package self.made.annotation.demo.presentation.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import self.made.annotation.demo.common.anntotation.MyNotBlank;
import self.made.annotation.demo.presentation.controller.request.DemoRequest;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@RestController
@Validated
public class DemoController {
    // NOTE: %E3%80%80(全角スペース)
    // NOTE: +(半角スペース)
    // NOTE: @NotBlankだと半角スペースはNG、全角スペースはOKとなってしまう

    // 用意されているアノテーションを使ってバリデーション
    @GetMapping("/demo")
    public String demo(@NotBlank @Valid @RequestParam("param") String param) {
        return "demo01: " + param;
    }

    // 自作アノテーションを使ってバリデーション①
    // NOTE: ConstraintViolationException のエラーハンドリングを書いていないので、500エラーを返す
    @GetMapping("/demo02")
    public String demo02(@MyNotBlank @Valid @RequestParam("param") String param) {
        return "demo02: " + param;
    }

    // 自作アノテーションを使ってバリデーション②
    // NOTE: BindExceptionをエラーハンドリングして400エラーを返す
    @GetMapping("/demo03")
    public String demo03(@Valid DemoRequest demoRequest) {
        return "demo03: " + demoRequest.getParam();
    }
}
