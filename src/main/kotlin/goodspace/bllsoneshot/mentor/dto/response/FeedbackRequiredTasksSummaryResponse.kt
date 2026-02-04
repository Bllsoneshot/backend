package goodspace.bllsoneshot.mentor.dto.response

data class FeedbackRequiredTasksSummaryResponse(
    val taskCount: Long,
    val menteeNames: List<String>
)
