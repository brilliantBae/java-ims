package codesquad.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import codesquad.UnAuthorizedException;
import codesquad.domain.Issue;
import codesquad.domain.MileStone;
import codesquad.domain.MileStoneRepository;
import codesquad.domain.User;
<<<<<<< HEAD
import codesquad.dto.MileStoneDto;
=======
>>>>>>> step3
import codesquad.security.LoginUser;

@Service
public class MileStoneService {
	@Resource(name = "mileStoneRepository")
	private MileStoneRepository mileStoneRepository;

	public void add(MileStoneDto mileStoneDto) {
		mileStoneRepository.save(mileStoneDto._toMileStone());
	}

	public List<MileStone> findAll() {
		return mileStoneRepository.findAll();
	}
<<<<<<< HEAD
//
//	public void register(@LoginUser User loginUser, Issue issue, MileStone mileStone) {
//		if (!issue.isSameUser(loginUser)) {
//			throw new UnAuthorizedException();
//		}
//		mileStone.addIssue(issue);
//		mileStoneRepository.save(mileStone);
//	}
=======

	public void register(@LoginUser User loginUser, Issue issue, MileStone mileStone) {
		if (!issue.isSameUser(loginUser)) {
			throw new UnAuthorizedException();
		}
		mileStone.addIssue(issue);
		mileStoneRepository.save(mileStone);
	}
>>>>>>> step3

	public MileStone findById(long id) {
		return mileStoneRepository.findOne(id);
	}

}
