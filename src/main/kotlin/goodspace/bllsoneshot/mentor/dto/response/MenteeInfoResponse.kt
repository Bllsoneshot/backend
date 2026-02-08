package goodspace.bllsoneshot.mentor.dto.response

import goodspace.bllsoneshot.entity.assignment.Subject

data class MenteeInfoResponse(
    val menteeId: Long,
    val menteeName: String,
    val grade: String?,
    val subjects: List<Subject>
)
