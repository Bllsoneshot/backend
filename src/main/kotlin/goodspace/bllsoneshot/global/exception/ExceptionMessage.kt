package goodspace.bllsoneshot.global.exception

enum class ExceptionMessage(
    val message: String
) {
    LOGIN_FAILED("아이디 혹은 비밀번호를 확인해 주세요."),
    INVALID_REFRESH_TOKEN("유효하지 않거나 만료된 리프레시 토큰입니다."),
    USER_NOT_FOUND("사용자를 찾을 수 없습니다."),
    TASK_NOT_FOUND("할 일을 찾을 수 없습니다."),
    FEEDBACK_NOT_FOUND("피드백이 존재하지 않습니다."),
    TASK_ACCESS_DENIED("해당 할 일에 대한 권한이 없습니다."),
    FILE_NOT_FOUND("파일을 찾을 수 없습니다."),
    CANNOT_COMPLETE_FUTURE_TASK("미래의 할 일은 완료할 수 없습니다."),
    INCOMPLETED_TASK("완료되지 않은 할 일입니다."),
    TASK_NOT_SUBMITTABLE("제출할 수 없는 할 일입니다."),
    CANNOT_DELETE_MENTOR_CREATED_TASK("멘토가 만든 할 일은 삭제할 수 없습니다."),
    NEGATIVE_ACTUAL_MINUTES("학습 시간은 음수일 수 없습니다."),
    DATES_REQUIRED("날짜는 최소 1개 이상 선택해야 합니다."),
    DUPLICATE_DATES_NOT_ALLOWED("중복된 날짜는 선택할 수 없습니다."),
    PAST_DATES_NOT_ALLOWED("과거 날짜는 선택할 수 없습니다."),

}
