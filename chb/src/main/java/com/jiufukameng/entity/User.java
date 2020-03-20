package com.jiufukameng.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;
import lombok.experimental.Accessors;

@JsonInclude(Include.NON_NULL)
public class User extends BaseEntity{
	
	
	private static final long serialVersionUID = -2527149704824641888L;
	
	private int id;
	private int pid; //上级id
	private long vipRankId; //星级
	private int userType; //用户类型;1:admin;2:会员
	private int sex;  //性别;0:保密,1:男,2:女
	private Date lastLoginTime; //最后登录时间
	private long score; //用户积分
	private BigDecimal balance; //余额
	private BigDecimal frozenBalance; //冻结余额
	private int userStatus; //用户状态;0:正常,1:禁用,2:未验证
	private String userlogin; //用户名
	private String userpass; //登录密码;cmf_password加密
	private String salt; //盐值
	private Integer userModel = 1 ; //默认为普通用户
	private Date lastLoginErrorTime; //最后一次登录错误时间
	private Integer loginErrorCount; //登录错误计数
	private Integer islocked; //是否锁定(0 未锁定,1 锁定)
	private Date firstLoginErrorTime;//第一次登录错误时间
	private String yzmCode; //图片验证码
	private String username; //用户姓名
	private String userEmail; //用户登录邮箱
	private String avatar; //用户头像
	private String lastLoginIp; //最后登录ip
	private String userActivationKey; //推荐码
	private String mobile; //用户手机号
	private String more; //扩展属性
	private int renzheng; //认证状态  0:未提交，1:已提交未认证，2:已认证，3:已拒绝
	private String erweima; //二维码
	private String poster; //推广海报
	private BigDecimal mActivationPrice; //月清交易量
	private BigDecimal zActivationPrice; //总交易量,为了升级
	private BigDecimal fActivationPrice; //自己的交易，为了分润
	private int highestId; //admin除外的最高级合伙人
	private  int integral; //用户积分（新增，用于兑换商品）
	private int tixianState; //提现状态 1可提现 2不可提现
	private int businessNum; //激活商户总数量（包括下级）
	private int zBusinessNum; //总商户数量
	private int partnerNum; //盟友总人数（直推+间推）
	private int fPosNum; //自己总机具数量
	private int zPosNum; //团队总机具数量
	private  int upgradePos; //pos数量，用于升级
	private String busiQrcode;	// 商户注册二维码
	private String frozenState;	//冻结状态，1未冻结 2已冻结不可提现
	private int stanBusNum; //达标商户总数量（包括下级）
	private String quickQrcode;	// 云闪付推广二维码
	private int userAgentId; //代理商id
	private String isReal;	//是否是真实用户，1真实用户 2虚拟用户
	private int collegeRankId; //财商学院等级：1普通会员
	private int etcRankId; //etc等级id
	private  String idcard;	//身份证号
	private  String realName;	// 真实姓名
	private  String carNum;	//etc车牌号
	private  String Commission;	//佣金金额，升级用
	private  int cardOrderNum; //团队累计订单数量（申卡+积分）
	private  String zCommission; //总佣金金额
	private  String agentpassword; //代理提现审核密码
	private  String txt; //所有上级id
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public long getVipRankId() {
		return vipRankId;
	}
	public void setVipRankId(long vipRankId) {
		this.vipRankId = vipRankId;
	}
	public int getUserType() {
		return userType;
	}
	public void setUserType(int userType) {
		this.userType = userType;
	}
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public Date getLastLoginTime() {
		return lastLoginTime;
	}
	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}
	public long getScore() {
		return score;
	}
	public void setScore(long score) {
		this.score = score;
	}
	public BigDecimal getBalance() {
		return balance;
	}
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
	public BigDecimal getFrozenBalance() {
		return frozenBalance;
	}
	public void setFrozenBalance(BigDecimal frozenBalance) {
		this.frozenBalance = frozenBalance;
	}
	public int getUserStatus() {
		return userStatus;
	}
	public void setUserStatus(int userStatus) {
		this.userStatus = userStatus;
	}
	public String getUserlogin() {
		return userlogin;
	}
	public void setUserlogin(String userlogin) {
		this.userlogin = userlogin;
	}
	public String getUserpass() {
		return userpass;
	}
	public void setUserpass(String userpass) {
		this.userpass = userpass;
	}
	public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}
	public Integer getUserModel() {
		return userModel;
	}
	public void setUserModel(Integer userModel) {
		this.userModel = userModel;
	}
	public Date getLastLoginErrorTime() {
		return lastLoginErrorTime;
	}
	public void setLastLoginErrorTime(Date lastLoginErrorTime) {
		this.lastLoginErrorTime = lastLoginErrorTime;
	}
	public Integer getLoginErrorCount() {
		return loginErrorCount;
	}
	public void setLoginErrorCount(Integer loginErrorCount) {
		this.loginErrorCount = loginErrorCount;
	}
	public Integer getIslocked() {
		return islocked;
	}
	public void setIslocked(Integer islocked) {
		this.islocked = islocked;
	}
	public Date getFirstLoginErrorTime() {
		return firstLoginErrorTime;
	}
	public void setFirstLoginErrorTime(Date firstLoginErrorTime) {
		this.firstLoginErrorTime = firstLoginErrorTime;
	}
	public String getYzmCode() {
		return yzmCode;
	}
	public void setYzmCode(String yzmCode) {
		this.yzmCode = yzmCode;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public String getLastLoginIp() {
		return lastLoginIp;
	}
	public void setLastLoginIp(String lastLoginIp) {
		this.lastLoginIp = lastLoginIp;
	}
	public String getUserActivationKey() {
		return userActivationKey;
	}
	public void setUserActivationKey(String userActivationKey) {
		this.userActivationKey = userActivationKey;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getMore() {
		return more;
	}
	public void setMore(String more) {
		this.more = more;
	}
	public int getRenzheng() {
		return renzheng;
	}
	public void setRenzheng(int renzheng) {
		this.renzheng = renzheng;
	}
	public String getErweima() {
		return erweima;
	}
	public void setErweima(String erweima) {
		this.erweima = erweima;
	}
	public String getPoster() {
		return poster;
	}
	public void setPoster(String poster) {
		this.poster = poster;
	}
	public BigDecimal getmActivationPrice() {
		return mActivationPrice;
	}
	public void setmActivationPrice(BigDecimal mActivationPrice) {
		this.mActivationPrice = mActivationPrice;
	}
	public BigDecimal getzActivationPrice() {
		return zActivationPrice;
	}
	public void setzActivationPrice(BigDecimal zActivationPrice) {
		this.zActivationPrice = zActivationPrice;
	}
	public BigDecimal getfActivationPrice() {
		return fActivationPrice;
	}
	public void setfActivationPrice(BigDecimal fActivationPrice) {
		this.fActivationPrice = fActivationPrice;
	}
	public int getHighestId() {
		return highestId;
	}
	public void setHighestId(int highestId) {
		this.highestId = highestId;
	}
	public int getIntegral() {
		return integral;
	}
	public void setIntegral(int integral) {
		this.integral = integral;
	}
	public int getTixianState() {
		return tixianState;
	}
	public void setTixianState(int tixianState) {
		this.tixianState = tixianState;
	}
	public int getBusinessNum() {
		return businessNum;
	}
	public void setBusinessNum(int businessNum) {
		this.businessNum = businessNum;
	}
	public int getzBusinessNum() {
		return zBusinessNum;
	}
	public void setzBusinessNum(int zBusinessNum) {
		this.zBusinessNum = zBusinessNum;
	}
	public int getPartnerNum() {
		return partnerNum;
	}
	public void setPartnerNum(int partnerNum) {
		this.partnerNum = partnerNum;
	}
	public int getfPosNum() {
		return fPosNum;
	}
	public void setfPosNum(int fPosNum) {
		this.fPosNum = fPosNum;
	}
	public int getzPosNum() {
		return zPosNum;
	}
	public void setzPosNum(int zPosNum) {
		this.zPosNum = zPosNum;
	}
	public int getUpgradePos() {
		return upgradePos;
	}
	public void setUpgradePos(int upgradePos) {
		this.upgradePos = upgradePos;
	}
	public String getBusiQrcode() {
		return busiQrcode;
	}
	public void setBusiQrcode(String busiQrcode) {
		this.busiQrcode = busiQrcode;
	}
	public String getFrozenState() {
		return frozenState;
	}
	public void setFrozenState(String frozenState) {
		this.frozenState = frozenState;
	}
	public int getStanBusNum() {
		return stanBusNum;
	}
	public void setStanBusNum(int stanBusNum) {
		this.stanBusNum = stanBusNum;
	}
	public String getQuickQrcode() {
		return quickQrcode;
	}
	public void setQuickQrcode(String quickQrcode) {
		this.quickQrcode = quickQrcode;
	}
	public int getUserAgentId() {
		return userAgentId;
	}
	public void setUserAgentId(int userAgentId) {
		this.userAgentId = userAgentId;
	}
	public String getIsReal() {
		return isReal;
	}
	public void setIsReal(String isReal) {
		this.isReal = isReal;
	}
	public int getCollegeRankId() {
		return collegeRankId;
	}
	public void setCollegeRankId(int collegeRankId) {
		this.collegeRankId = collegeRankId;
	}
	public int getEtcRankId() {
		return etcRankId;
	}
	public void setEtcRankId(int etcRankId) {
		this.etcRankId = etcRankId;
	}
	public String getIdcard() {
		return idcard;
	}
	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getCarNum() {
		return carNum;
	}
	public void setCarNum(String carNum) {
		this.carNum = carNum;
	}
	public String getCommission() {
		return Commission;
	}
	public void setCommission(String commission) {
		Commission = commission;
	}
	public int getCardOrderNum() {
		return cardOrderNum;
	}
	public void setCardOrderNum(int cardOrderNum) {
		this.cardOrderNum = cardOrderNum;
	}
	public String getzCommission() {
		return zCommission;
	}
	public void setzCommission(String zCommission) {
		this.zCommission = zCommission;
	}
	public String getAgentpassword() {
		return agentpassword;
	}
	public void setAgentpassword(String agentpassword) {
		this.agentpassword = agentpassword;
	}
	public String getTxt() {
		return txt;
	}
	public void setTxt(String txt) {
		this.txt = txt;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Commission == null) ? 0 : Commission.hashCode());
		result = prime * result + ((agentpassword == null) ? 0 : agentpassword.hashCode());
		result = prime * result + ((avatar == null) ? 0 : avatar.hashCode());
		result = prime * result + ((balance == null) ? 0 : balance.hashCode());
		result = prime * result + ((busiQrcode == null) ? 0 : busiQrcode.hashCode());
		result = prime * result + businessNum;
		result = prime * result + ((carNum == null) ? 0 : carNum.hashCode());
		result = prime * result + cardOrderNum;
		result = prime * result + collegeRankId;
		result = prime * result + ((erweima == null) ? 0 : erweima.hashCode());
		result = prime * result + etcRankId;
		result = prime * result + ((fActivationPrice == null) ? 0 : fActivationPrice.hashCode());
		result = prime * result + fPosNum;
		result = prime * result + ((firstLoginErrorTime == null) ? 0 : firstLoginErrorTime.hashCode());
		result = prime * result + ((frozenBalance == null) ? 0 : frozenBalance.hashCode());
		result = prime * result + ((frozenState == null) ? 0 : frozenState.hashCode());
		result = prime * result + highestId;
		result = prime * result + id;
		result = prime * result + ((idcard == null) ? 0 : idcard.hashCode());
		result = prime * result + integral;
		result = prime * result + ((isReal == null) ? 0 : isReal.hashCode());
		result = prime * result + ((islocked == null) ? 0 : islocked.hashCode());
		result = prime * result + ((lastLoginErrorTime == null) ? 0 : lastLoginErrorTime.hashCode());
		result = prime * result + ((lastLoginIp == null) ? 0 : lastLoginIp.hashCode());
		result = prime * result + ((lastLoginTime == null) ? 0 : lastLoginTime.hashCode());
		result = prime * result + ((loginErrorCount == null) ? 0 : loginErrorCount.hashCode());
		result = prime * result + ((mActivationPrice == null) ? 0 : mActivationPrice.hashCode());
		result = prime * result + ((mobile == null) ? 0 : mobile.hashCode());
		result = prime * result + ((more == null) ? 0 : more.hashCode());
		result = prime * result + partnerNum;
		result = prime * result + pid;
		result = prime * result + ((poster == null) ? 0 : poster.hashCode());
		result = prime * result + ((quickQrcode == null) ? 0 : quickQrcode.hashCode());
		result = prime * result + ((realName == null) ? 0 : realName.hashCode());
		result = prime * result + renzheng;
		result = prime * result + ((salt == null) ? 0 : salt.hashCode());
		result = prime * result + (int) (score ^ (score >>> 32));
		result = prime * result + sex;
		result = prime * result + stanBusNum;
		result = prime * result + tixianState;
		result = prime * result + ((txt == null) ? 0 : txt.hashCode());
		result = prime * result + upgradePos;
		result = prime * result + ((userActivationKey == null) ? 0 : userActivationKey.hashCode());
		result = prime * result + userAgentId;
		result = prime * result + ((userEmail == null) ? 0 : userEmail.hashCode());
		result = prime * result + ((userModel == null) ? 0 : userModel.hashCode());
		result = prime * result + userStatus;
		result = prime * result + userType;
		result = prime * result + ((userlogin == null) ? 0 : userlogin.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		result = prime * result + ((userpass == null) ? 0 : userpass.hashCode());
		result = prime * result + (int) (vipRankId ^ (vipRankId >>> 32));
		result = prime * result + ((yzmCode == null) ? 0 : yzmCode.hashCode());
		result = prime * result + ((zActivationPrice == null) ? 0 : zActivationPrice.hashCode());
		result = prime * result + zBusinessNum;
		result = prime * result + ((zCommission == null) ? 0 : zCommission.hashCode());
		result = prime * result + zPosNum;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (Commission == null) {
			if (other.Commission != null)
				return false;
		} else if (!Commission.equals(other.Commission))
			return false;
		if (agentpassword == null) {
			if (other.agentpassword != null)
				return false;
		} else if (!agentpassword.equals(other.agentpassword))
			return false;
		if (avatar == null) {
			if (other.avatar != null)
				return false;
		} else if (!avatar.equals(other.avatar))
			return false;
		if (balance == null) {
			if (other.balance != null)
				return false;
		} else if (!balance.equals(other.balance))
			return false;
		if (busiQrcode == null) {
			if (other.busiQrcode != null)
				return false;
		} else if (!busiQrcode.equals(other.busiQrcode))
			return false;
		if (businessNum != other.businessNum)
			return false;
		if (carNum == null) {
			if (other.carNum != null)
				return false;
		} else if (!carNum.equals(other.carNum))
			return false;
		if (cardOrderNum != other.cardOrderNum)
			return false;
		if (collegeRankId != other.collegeRankId)
			return false;
		if (erweima == null) {
			if (other.erweima != null)
				return false;
		} else if (!erweima.equals(other.erweima))
			return false;
		if (etcRankId != other.etcRankId)
			return false;
		if (fActivationPrice == null) {
			if (other.fActivationPrice != null)
				return false;
		} else if (!fActivationPrice.equals(other.fActivationPrice))
			return false;
		if (fPosNum != other.fPosNum)
			return false;
		if (firstLoginErrorTime == null) {
			if (other.firstLoginErrorTime != null)
				return false;
		} else if (!firstLoginErrorTime.equals(other.firstLoginErrorTime))
			return false;
		if (frozenBalance == null) {
			if (other.frozenBalance != null)
				return false;
		} else if (!frozenBalance.equals(other.frozenBalance))
			return false;
		if (frozenState == null) {
			if (other.frozenState != null)
				return false;
		} else if (!frozenState.equals(other.frozenState))
			return false;
		if (highestId != other.highestId)
			return false;
		if (id != other.id)
			return false;
		if (idcard == null) {
			if (other.idcard != null)
				return false;
		} else if (!idcard.equals(other.idcard))
			return false;
		if (integral != other.integral)
			return false;
		if (isReal == null) {
			if (other.isReal != null)
				return false;
		} else if (!isReal.equals(other.isReal))
			return false;
		if (islocked == null) {
			if (other.islocked != null)
				return false;
		} else if (!islocked.equals(other.islocked))
			return false;
		if (lastLoginErrorTime == null) {
			if (other.lastLoginErrorTime != null)
				return false;
		} else if (!lastLoginErrorTime.equals(other.lastLoginErrorTime))
			return false;
		if (lastLoginIp == null) {
			if (other.lastLoginIp != null)
				return false;
		} else if (!lastLoginIp.equals(other.lastLoginIp))
			return false;
		if (lastLoginTime == null) {
			if (other.lastLoginTime != null)
				return false;
		} else if (!lastLoginTime.equals(other.lastLoginTime))
			return false;
		if (loginErrorCount == null) {
			if (other.loginErrorCount != null)
				return false;
		} else if (!loginErrorCount.equals(other.loginErrorCount))
			return false;
		if (mActivationPrice == null) {
			if (other.mActivationPrice != null)
				return false;
		} else if (!mActivationPrice.equals(other.mActivationPrice))
			return false;
		if (mobile == null) {
			if (other.mobile != null)
				return false;
		} else if (!mobile.equals(other.mobile))
			return false;
		if (more == null) {
			if (other.more != null)
				return false;
		} else if (!more.equals(other.more))
			return false;
		if (partnerNum != other.partnerNum)
			return false;
		if (pid != other.pid)
			return false;
		if (poster == null) {
			if (other.poster != null)
				return false;
		} else if (!poster.equals(other.poster))
			return false;
		if (quickQrcode == null) {
			if (other.quickQrcode != null)
				return false;
		} else if (!quickQrcode.equals(other.quickQrcode))
			return false;
		if (realName == null) {
			if (other.realName != null)
				return false;
		} else if (!realName.equals(other.realName))
			return false;
		if (renzheng != other.renzheng)
			return false;
		if (salt == null) {
			if (other.salt != null)
				return false;
		} else if (!salt.equals(other.salt))
			return false;
		if (score != other.score)
			return false;
		if (sex != other.sex)
			return false;
		if (stanBusNum != other.stanBusNum)
			return false;
		if (tixianState != other.tixianState)
			return false;
		if (txt == null) {
			if (other.txt != null)
				return false;
		} else if (!txt.equals(other.txt))
			return false;
		if (upgradePos != other.upgradePos)
			return false;
		if (userActivationKey == null) {
			if (other.userActivationKey != null)
				return false;
		} else if (!userActivationKey.equals(other.userActivationKey))
			return false;
		if (userAgentId != other.userAgentId)
			return false;
		if (userEmail == null) {
			if (other.userEmail != null)
				return false;
		} else if (!userEmail.equals(other.userEmail))
			return false;
		if (userModel == null) {
			if (other.userModel != null)
				return false;
		} else if (!userModel.equals(other.userModel))
			return false;
		if (userStatus != other.userStatus)
			return false;
		if (userType != other.userType)
			return false;
		if (userlogin == null) {
			if (other.userlogin != null)
				return false;
		} else if (!userlogin.equals(other.userlogin))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		if (userpass == null) {
			if (other.userpass != null)
				return false;
		} else if (!userpass.equals(other.userpass))
			return false;
		if (vipRankId != other.vipRankId)
			return false;
		if (yzmCode == null) {
			if (other.yzmCode != null)
				return false;
		} else if (!yzmCode.equals(other.yzmCode))
			return false;
		if (zActivationPrice == null) {
			if (other.zActivationPrice != null)
				return false;
		} else if (!zActivationPrice.equals(other.zActivationPrice))
			return false;
		if (zBusinessNum != other.zBusinessNum)
			return false;
		if (zCommission == null) {
			if (other.zCommission != null)
				return false;
		} else if (!zCommission.equals(other.zCommission))
			return false;
		if (zPosNum != other.zPosNum)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", pid=" + pid + ", vipRankId=" + vipRankId + ", userType=" + userType + ", sex="
				+ sex + ", lastLoginTime=" + lastLoginTime + ", score=" + score + ", balance=" + balance
				+ ", frozenBalance=" + frozenBalance + ", userStatus=" + userStatus + ", userlogin=" + userlogin
				+ ", userpass=" + userpass + ", salt=" + salt + ", userModel=" + userModel + ", lastLoginErrorTime="
				+ lastLoginErrorTime + ", loginErrorCount=" + loginErrorCount + ", islocked=" + islocked
				+ ", firstLoginErrorTime=" + firstLoginErrorTime + ", yzmCode=" + yzmCode + ", username=" + username
				+ ", userEmail=" + userEmail + ", avatar=" + avatar + ", lastLoginIp=" + lastLoginIp
				+ ", userActivationKey=" + userActivationKey + ", mobile=" + mobile + ", more=" + more + ", renzheng="
				+ renzheng + ", erweima=" + erweima + ", poster=" + poster + ", mActivationPrice=" + mActivationPrice
				+ ", zActivationPrice=" + zActivationPrice + ", fActivationPrice=" + fActivationPrice + ", highestId="
				+ highestId + ", integral=" + integral + ", tixianState=" + tixianState + ", businessNum=" + businessNum
				+ ", zBusinessNum=" + zBusinessNum + ", partnerNum=" + partnerNum + ", fPosNum=" + fPosNum
				+ ", zPosNum=" + zPosNum + ", upgradePos=" + upgradePos + ", busiQrcode=" + busiQrcode
				+ ", frozenState=" + frozenState + ", stanBusNum=" + stanBusNum + ", quickQrcode=" + quickQrcode
				+ ", userAgentId=" + userAgentId + ", isReal=" + isReal + ", collegeRankId=" + collegeRankId
				+ ", etcRankId=" + etcRankId + ", idcard=" + idcard + ", realName=" + realName + ", carNum=" + carNum
				+ ", Commission=" + Commission + ", cardOrderNum=" + cardOrderNum + ", zCommission=" + zCommission
				+ ", agentpassword=" + agentpassword + ", txt=" + txt + "]";
	}
	
	
	
}