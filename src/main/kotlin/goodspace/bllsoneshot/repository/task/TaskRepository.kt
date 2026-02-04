package goodspace.bllsoneshot.repository.task

import goodspace.bllsoneshot.entity.assignment.CommentType
import goodspace.bllsoneshot.entity.assignment.RegisterStatus
import goodspace.bllsoneshot.entity.assignment.Subject
import goodspace.bllsoneshot.entity.assignment.Task
import goodspace.bllsoneshot.mentor.dto.response.FeedbackRequiredMenteeResponse
import java.time.LocalDate
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface TaskRepository : JpaRepository<Task, Long> {

    @Query(
        """
        SELECT t FROM Task t
        WHERE t.mentee.id = :userId
        AND (t.startDate IS NULL OR :date >= t.startDate)
        AND (t.dueDate IS NULL OR :date <= t.dueDate)
        """
    )
    fun findCurrentTasks(userId: Long, date: LocalDate): List<Task>

    @Query(
        """
        SELECT t FROM Task t
        WHERE t.mentee.id = :userId
        AND (t.startDate IS NULL OR :date >= t.startDate)
        AND t.dueDate IS NOT NULL AND :date <= t.dueDate
        """
    )
    fun findCurrentTasksDueDateExists(userId: Long, date: LocalDate): List<Task>

    @Query(
        """
        SELECT t FROM Task t
        WHERE t.mentee.id = :userId AND t.subject = :subject
        AND (t.dueDate IS NULL OR t.dueDate < :date)
        """
    )
    fun findPreviousTasks(userId: Long, subject: Subject, date: LocalDate): List<Task>

    @Query(
        """
        SELECT DISTINCT t FROM Task t
        LEFT JOIN FETCH t.mentee m
        LEFT JOIN FETCH m.mentor
        LEFT JOIN FETCH t.generalComment
        LEFT JOIN FETCH t.proofShots
        WHERE t.id = :taskId
        """
    )
    fun findByIdWithMenteeAndGeneralCommentAndProofShots(taskId: Long): Task?

    /*
    1. Task -> mentee 조인 => 멘티 정보 가져오기
    2. Task & mentee -> proofShots 조인 => 증빙샷이 있는 Task 필터링됨
    3. AND NOT EXISTS 서브쿼리 => 해당 Task에 대해 피드백 댓글이 없는 것만 필터링
    4. GROUP BY mentee.id, mentee.name => 멘티별로 그룹화
    5. SELECT new ... => 멘티 ID, 이름, 피드백이 필요한 Task 수를 DTO로 매핑
     */
    @Query(
        """
        SELECT new goodspace.bllsoneshot.mentor.dto.response.FeedbackRequiredMenteeResponse(
            m.id,
            m.name,
            COUNT(DISTINCT t.id)
        )
        FROM Task t
        JOIN t.mentee m
        JOIN t.proofShots ps
        WHERE m.mentor.id = :mentorId
        AND NOT EXISTS (
            SELECT c 
            FROM Comment c
            WHERE c.task = t
              AND c.type = :feedbackType
              AND c.registerStatus = :registeredStatus
        )
        GROUP BY m.id, m.name
        """
    )
    fun findFeedbackRequiredMentees(
        mentorId: Long,
        feedbackType: CommentType,
        registeredStatus: RegisterStatus
    ): List<FeedbackRequiredMenteeResponse>
}
