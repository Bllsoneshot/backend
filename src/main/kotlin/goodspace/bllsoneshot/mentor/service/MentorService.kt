package goodspace.bllsoneshot.mentor.service

import goodspace.bllsoneshot.global.exception.ExceptionMessage.MENTEE_ACCESS_DENIED
import goodspace.bllsoneshot.global.exception.ExceptionMessage.USER_NOT_FOUND
import goodspace.bllsoneshot.mentor.dto.response.MenteeInfoResponse
import goodspace.bllsoneshot.repository.user.UserRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class MentorService(
    private val userRepository: UserRepository
) {
    @Transactional(readOnly = true)
    fun getMenteeInfo(mentorId: Long, menteeId: Long): MenteeInfoResponse {
        val mentee = userRepository.findMenteeWithSubjectsById(menteeId)
            ?: throw IllegalArgumentException(USER_NOT_FOUND.message)

        if (mentee.mentor?.id != mentorId) {
            throw IllegalArgumentException(MENTEE_ACCESS_DENIED.message)
        }

        return MenteeInfoResponse(
            menteeId = mentee.id!!,
            menteeName = mentee.name,
            grade = mentee.grade,
            subjects = mentee.subjects.map { it.subject }
        )
    }
}
