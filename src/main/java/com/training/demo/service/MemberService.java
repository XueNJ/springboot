package com.training.demo.service;

import java.io.IOException;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.training.demo.oracle.dao.MemberCriteriaQueryDao;
import com.training.demo.oracle.dao.MemberDMLOperationsDao;
import com.training.demo.oracle.entity.Bevrage_Member;
import com.training.demo.vo.InputMemberLogin;
import com.training.demo.vo.InputMemberModify;
import com.training.demo.vo.OutputLogin;
import com.training.demo.vo.OutputLogout;
import com.training.demo.vo.OutputMember;
import com.training.demo.vo.OutputMemberModify;
import com.training.demo.vo.OutputResultMessage;
import com.training.demo.vo.SessionMember;

@Service
public class MemberService {

	// ------------------------------ Autowired ------------------------------
	@Autowired
	private MemberCriteriaQueryDao memberCriteriaQueryDao;
	@Autowired
	private MemberDMLOperationsDao memberDMLOperationsDao;
	@Resource(name = "sessionScopedBeanSessionMember")
	private SessionMember sessionScopedBeanMember;
	@Autowired
	private SessionMember sessionMember;
	@Autowired
	private InputMemberLogin inputMemberLogin;
	@Autowired
	private OutputMember outputMember;
	@Autowired
	private OutputMemberModify outputMemberModify;
	@Autowired
	private OutputResultMessage outputResultMessage;
	@Autowired
	private OutputLogin outputLogin;
	@Autowired
	private OutputLogout outputLogout;

	// ------------------------------ Serveice Method ------------------------------
	/*
	 * Login :
	 */
	public OutputLogin login(InputMemberLogin vo) throws IOException {
		boolean boolResult = false;
		String strMessage = "";
		try {
			// 執行資料
			outputMember = memberCriteriaQueryDao.queryMember(vo);
			// 檢核 InVo && OutVo
			if (outputMember.getId() == null) {
				boolResult = false;
				strMessage = "查無此人!";
			} else if (!outputMember.getPwd().equals(vo.getPwd())) {
				boolResult = false;
				strMessage = "密碼有誤!";
			} else if (!outputMember.getStatus().equals("1")) {
				boolResult = false;
				strMessage = "此用戶停用中!";
			} else {
				boolResult = true;
			}

		} catch (Exception ex) {
			boolResult = false;
			strMessage = "系統錯誤!";
		}
		// 登入成功 input session
		if (boolResult) {
			sessionScopedBeanMember.setId(outputMember.getId());
			sessionScopedBeanMember.setName(outputMember.getName());
			sessionScopedBeanMember.setPwd(outputMember.getPwd());
			sessionScopedBeanMember.setStatus(outputMember.getStatus());
			outputLogin.setOutputResultMessage(outputResultMessage);
			boolResult = true;
			strMessage = "登入成功!";
		}
		// set ouput message
		outputResultMessage.setResult(boolResult == true ? "S" : "E");
		outputResultMessage.setMessage(strMessage);
		outputLogin.setOutputResultMessage(outputResultMessage);
		return outputLogin;
	}

	/*
	 * Logout :
	 */
	public OutputLogout logout() {
		boolean boolResult = false;
		String strMessage = "";
		outputMember = outputMember.init();
		if (sessionScopedBeanMember.getId() == null) {
			strMessage = "請先登入!";
		} else {
			try {
				outputMember.setId(sessionScopedBeanMember.getId());
				outputMember.setName(sessionScopedBeanMember.getName());
				outputMember.setPwd(sessionScopedBeanMember.getPwd());
				outputMember.setStatus(sessionScopedBeanMember.getStatus());
				sessionScopedBeanMember = sessionMember.init();
				if (sessionScopedBeanMember.getId() == null) {
					boolResult = true;
					strMessage = "登出成功！";
				} else {
					strMessage = "登出失敗！";
				}
			} catch (Exception ex) {
				strMessage = "系統錯誤！";
			}
		}
		outputResultMessage.setResult(boolResult == true ? "S" : "E");
		outputResultMessage.setMessage(strMessage);
		outputLogout.setOutputMember(outputMember);
		outputLogout.setOutputResultMessage(outputResultMessage);
		return outputLogout;
	}

	/*
	 * Register :
	 */
	public OutputMemberModify memberModify(InputMemberModify vo) throws IOException {
		boolean boolResult = false;
		String strMessage = "";
		outputMember = outputMember.init();
		outputMemberModify = outputMemberModify.init();
		try {
			// 執行資料
			inputMemberLogin.setId(vo.getId());
			outputMember = memberCriteriaQueryDao.queryMember(inputMemberLogin); // 撈取是否重複註冊
			// 檢核 InVo && OutVo
			if (vo.getId().equals("") && vo.getName().equals("") && vo.getPwd().equals("")
					&& vo.getStatus().equals("")) {
				strMessage = "欄位不可為空值!";
			} else if (outputMember.getId() != null && vo.getAction().equals("Create")) {
				strMessage = "此帳號重複註冊!";
			} else if (!vo.getPwd().equals(vo.getPwdCheck())) {
				strMessage = "密碼確認有誤!";
			} else {
				boolResult = true;
			}

			if (boolResult) {
				Bevrage_Member member = Bevrage_Member.builder().identificationNO(vo.getId()).password(vo.getPwd())
						.customerName(vo.getName()).status(vo.getStatus()).build();
				memberDMLOperationsDao.save(member);
				outputMember = outputMember.init();
				outputMember.setId(member.getIdentificationNO());
				outputMember.setName(member.getCustomerName());
				outputMember.setPwd(member.getPassword());
				outputMember.setStatus(member.getStatus());
				outputMemberModify.setOutputMember(outputMember);
				strMessage = outputMember != null ? "新增成功!" : "更新成功!";
			}
		} catch (Exception ex) {
			strMessage = "系統錯誤!";
		}
		// set ouput message
		outputResultMessage.setResult(boolResult == true ? "S" : "E");
		outputResultMessage.setMessage(strMessage);
		outputMemberModify.setOutputResultMessage(outputResultMessage);
		return outputMemberModify;
	}

}
