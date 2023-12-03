package hello.springmvc.basic.request;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

@Slf4j
@Controller
public class RequestBodyStringController {

    @PostMapping("/request-body-string-v1")
    public void requestBodyString(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ServletInputStream inputStream = request.getInputStream(); // 스트림은 바이트 코드이다
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);// -> 바이트코드를 어떤 인코딩으로 변환해서 받을지 지정해줘야 한다.

        log.info("messageBody = {}", messageBody);
        response.getWriter().write("ok");
    }

    @PostMapping("/request-body-string-v2")
    public void requestBodyStringV2(InputStream inputStream, Writer responseWriter) throws IOException { // 매개변수로 InputStream으로 바이트 코드를 바로 받을 수 있다
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        log.info("messageBody = {}", messageBody);
        responseWriter.write("ok"); // response의 writer 또한 매개변수로 지정해 사용할 수 있다.
    }

    @PostMapping("/request-body-string-v3")
    public HttpEntity<String> requestBodyStringV3(HttpEntity<String> httpEntity) throws IOException {

        String messageBody = httpEntity.getBody(); // 메시지 바디에 있는 내용을 꺼낼 수 있다.
        log.info("messageBody = {}", messageBody);

        return new HttpEntity<>("ok");
    }


}
