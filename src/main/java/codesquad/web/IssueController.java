package codesquad.web;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import codesquad.domain.Issue;
import codesquad.domain.User;
import codesquad.dto.IssueDto;
import codesquad.security.LoginUser;
import codesquad.service.IssueService;
import support.domain.AbstractEntity;
@Controller
@RequestMapping("/issues")
public class IssueController extends AbstractEntity {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    
    @Resource(name = "issueService")
    private IssueService issueService;
    
    @GetMapping("/form")
    public String form(@LoginUser User loginUser) {
    		return "/issue/form";
    }
    
    // view로 부터 dto 형태로 데이터를 받아 처리.
    @PostMapping("")
    public String create(IssueDto issueDto, @LoginUser User loginUser) {
    		if(issueDto.isSubjectBlank() || issueDto.isCommentBlank()) {
    			return "redirect:/issues";
    		}
    		issueService.add(issueDto, loginUser);
    		return "redirect:/";
    }
    
    @GetMapping("/{id}/form")
    public String updateForm(@LoginUser User loginUser, @PathVariable long id, Model model) {
    		Issue issue = issueService.findById(id);
    		if(!issue.isSameUser(loginUser)) {
    			return "redirect:/users/loginForm";
    		}
    		model.addAttribute("issue", issue);
    		return "/issue/updateForm";
    }
    
    @PutMapping("/{id}")
    public String update(@LoginUser User loginUser, @PathVariable long id, String subject, String comment, Model model) {
    		Issue issue = issueService.update(loginUser, id, subject, comment);
    		model.addAttribute("issue", issue);
    		return "/issue/show";
    }
    
    @DeleteMapping("/{id}")
    public String delete(@LoginUser User loginUser, @PathVariable long id) {
    		issueService.delete(loginUser, id);
    		return "redirect:/";
    }
    
    @GetMapping("/{id}")
    public String show(@PathVariable long id, Model model) {
    		// model 을 통해 출력할 이슈를 전달.
    		Issue issue = issueService.findById(id);
    		log.debug("issue : {}", issue);
    		model.addAttribute("issue", issue);
    		return "/issue/show";
    }
}