package self.made.annotation.demo.presentation.controller.request;

import lombok.Data;
import self.made.annotation.demo.common.anntotation.MyNotBlank;

@Data
public class DemoRequest {
    @MyNotBlank
    private String param;
}
