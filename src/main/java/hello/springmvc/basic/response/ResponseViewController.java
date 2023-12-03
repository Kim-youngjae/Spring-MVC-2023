package hello.springmvc.basic.response;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ResponseViewController {

    @RequestMapping("/response-view-v1")
    public ModelAndView responseViewV1() {
        ModelAndView mav = new ModelAndView("/reponse/hello") // /reponse/hello라는 경로로 뷰를 만들고
                .addObject("data", "hello!"); // 이 경로에 data라는 이름을 가진 속성에 hello!를 넣겠다
        return mav;
    }

    @RequestMapping("/response-view-v2")
    public String responseViewV2(Model model) { // @Controller이고 반환 값이 String이면 해당 경로를 찾아 간다.
        model.addAttribute("data", "hello! V2");
        return "response/hello";
    }


}
