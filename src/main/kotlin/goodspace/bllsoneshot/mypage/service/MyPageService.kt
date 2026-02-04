package goodspace.bllsoneshot.mypage.service

import goodspace.bllsoneshot.entity.assignment.Subject
import goodspace.bllsoneshot.mypage.dto.response.LearningStatusResponse
import goodspace.bllsoneshot.mypage.mapper.LearningStatusMapper
import goodspace.bllsoneshot.repository.task.TaskRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate

@Service
class MyPageService(
    private val taskRepository: TaskRepository,
    private val learningStatusMapper: LearningStatusMapper
) {

    @Transactional(readOnly = true)
    fun getTotalLearningStatus(userId: Long, date: LocalDate): List<LearningStatusResponse> {
        val tasks = taskRepository.findByMenteeIdAndDate(userId, date)

        return Subject.entries
            .map { learningStatusMapper.map(it, tasks) }
    }
}
