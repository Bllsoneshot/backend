package goodspace.bllsoneshot.mentor.dto.response

data class FeedbackRequiredMenteeResponse(
    val menteeId: Long,
    val menteeName: String,
    val submittedTaskCount: Long
)
