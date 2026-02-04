package goodspace.bllsoneshot.mypage.controller

import goodspace.bllsoneshot.global.security.userId
import goodspace.bllsoneshot.mypage.dto.response.LearningStatusResponse
import goodspace.bllsoneshot.mypage.service.MyPageService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import java.security.Principal
import java.time.LocalDate
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/users/me")
@Tag(
    name = "마이 페이지 API"
)
class MyPageController(
    private val myPageService: MyPageService
) {

    @GetMapping("/learning-status")
    @Operation(
        summary = "전체 학습 현황 조회",
        description = """
            과목별로, '완료된 할 일'의 개수와 '할 일'의 개수를 조회합니다.
            
            [요청]
            date: 조회할 할 일의 날짜(yyyy-MM-dd)
            
            [응답]
            subject: 과목(KOREAN, ENGLISH, MATH)
            taskAmount: 해당 과목의 할 일 개수
            completedTaskAmount: 해당 과목의 완료된 할 일 개수
        """
    )
    fun getTotalLearningStatus(
        principal: Principal,
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) date: LocalDate
    ): ResponseEntity<List<LearningStatusResponse>> {
        val userId = principal.userId

        val response = myPageService.getTotalLearningStatus(userId, date)

        return ResponseEntity.ok(response)
    }
}
