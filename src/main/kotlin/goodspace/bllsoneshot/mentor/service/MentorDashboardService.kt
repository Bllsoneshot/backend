package goodspace.bllsoneshot.mentor.service

import goodspace.bllsoneshot.entity.assignment.CommentType
import goodspace.bllsoneshot.entity.assignment.RegisterStatus
import goodspace.bllsoneshot.global.exception.ExceptionMessage.USER_NOT_FOUND
import goodspace.bllsoneshot.mentor.dto.response.FeedbackRequiredMenteeResponse
import goodspace.bllsoneshot.repository.task.TaskRepository
import goodspace.bllsoneshot.repository.user.UserRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class MentorDashboardService(
    private val taskRepository: TaskRepository,
    private val userRepository: UserRepository
) {
    @Transactional(readOnly = true)
    fun getFeedbackRequiredMentees(mentorId: Long): List<FeedbackRequiredMenteeResponse> {
        userRepository.findById(mentorId)
            .orElseThrow { IllegalArgumentException(USER_NOT_FOUND.message) }

        return taskRepository.findFeedbackRequiredMentees(
            mentorId = mentorId,
            feedbackType = CommentType.FEEDBACK,
            registeredStatus = RegisterStatus.REGISTERED
        )
    }
}
