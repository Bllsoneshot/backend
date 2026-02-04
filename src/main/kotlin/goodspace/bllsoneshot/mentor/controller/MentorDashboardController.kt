package goodspace.bllsoneshot.mentor.controller

import goodspace.bllsoneshot.global.security.userId
import goodspace.bllsoneshot.mentor.dto.response.FeedbackRequiredMenteeResponse
import goodspace.bllsoneshot.mentor.service.MentorDashboardService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import java.security.Principal
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/mentors/me/dashboard")
@Tag(name = "멘토 대시보드 API")
class MentorDashboardController(
    private val mentorDashboardService: MentorDashboardService
) {
    @GetMapping("/pending-feedback")
    @Operation(
        summary = "오늘 확인해야 할 항목 조회",
        description = """
            멘토가 담당하는 멘티 중,
            인증 사진을 제출했지만(ProofShot 존재) 피드백이 등록되지 않은 과제만 집계합니다.
            
            [응답]
            menteeId: 멘티 ID
            menteeName: 멘티 이름
            submittedTaskCount: 제출된 과제 건수(피드백 미작성)
        """
    )
    fun getFeedbackRequiredMentees(
        principal: Principal
    ): ResponseEntity<List<FeedbackRequiredMenteeResponse>> {
        val mentorId = principal.userId
        val response = mentorDashboardService.getFeedbackRequiredMentees(mentorId)
        return ResponseEntity.ok(response)
    }
}
