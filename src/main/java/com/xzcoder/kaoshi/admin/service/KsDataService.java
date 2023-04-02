package com.xzcoder.kaoshi.admin.service;

import java.util.List;

import com.xzcoder.kaoshi.common.po.ExamStResult;
import com.xzcoder.kaoshi.common.po.PageBean;
import com.xzcoder.kaoshi.po.ExamKsDataCustom;
import com.xzcoder.kaoshi.po.ExamKsMonitor;
import com.xzcoder.kaoshi.po.SysGroupCustom;
import com.xzcoder.kaoshi.po.SysUserCustom;
import com.xzcoder.kaoshi.vo.KsDataQueryVo;
import com.xzcoder.kaoshi.vo.KsMonitorQueryVo;

/**
 * KsDataService
 *
 * @author <a href="https://xzcoder.com">朱帅</a>
 */
public interface KsDataService {

	/**
	 * 组合条件分页查询考试信息
	 * @param ksDataQueryVo
	 * @param pageBean
	 * @return
	 * @throws Exception
	 */
	public List<ExamKsDataCustom> findKsDataByVo(KsDataQueryVo ksDataQueryVo, PageBean pageBean) throws Exception;

	/**
	 * 添加新的考试信息
	 * 添加需要考试的用户关系
	 * @param ksDataCustom
	 * @param userIds
	 * @return
	 * @throws Exception
	 */
	public Integer insertKsData(ExamKsDataCustom ksDataCustom, SysUserCustom user, Integer[] userIds) throws Exception;

	/**
	 * 根据id获取考试信息
	 * @param Id
	 * @return
	 * @throws Exception
	 */
	public ExamKsDataCustom getKsDataById(Integer id) throws Exception;

	/**
	 * 更新考试日期
	 * @param ksDataCustom
	 * @throws Exception
	 */
	public void updateKsData(ExamKsDataCustom ksDataCustom, SysUserCustom user) throws Exception;

	/**
	 * 根据id删除考试信息
	 * @param ksId
	 * @param user
	 * @throws Exception
	 */
	public void deleteKsDataById(Integer ksId, SysUserCustom user) throws Exception;

	/**
	 * 将该场考试的结束时间和当前时间作比较
	 * 若当前时间小于考试开始时间：不能进行考试
	 * 若当前时间大于等于考试结束时间：不能进行考试
	 * 否则可以进行考试
	 * @param kuId
	 * @return
	 * @throws Exception
	 */
	public String isCanBeginKs(Integer kuId) throws Exception;

	/**
	 * 判断是否可以交卷
	 * 返回-1表示可以交卷
	 * 不能交卷返回disableSubmit值
	 * @param ksId
	 * @return
	 * @throws Exception
	 */
	public Integer isCanSubmitExam(Integer ksId, Integer kuId) throws Exception;

	/**
	 * 获取所有有效考试的编号和名称的集合
	 * @return
	 * @throws Exception
	 */
	public List<ExamKsMonitor> getAllExamIdAndNameList() throws Exception;

	/**
	 * 分页、组合条件查询考试监控信息
	 * @param ksMonitorQueryVo
	 * @param pageBean
	 * @return
	 * @throws Exception
	 */
	public List<ExamKsMonitor> findKsMonitorByVo(KsMonitorQueryVo ksMonitorQueryVo, PageBean pageBean) throws Exception;

	public boolean isCanAddUser(Integer ksId) throws Exception;

	/**
	 * 获取考试错题统计结果
	 * @param ksId
	 * @return
	 * @throws Exception
	 */
	public List<ExamStResult> getKsErrorResult(Integer ksId, Integer groupId) throws Exception;

	/**
	 * 获取该考试对应的学生的分组集合
	 * @param ksId
	 * @return
	 * @throws Exception
	 */
	public List<SysGroupCustom> getKsUserGroupsByKsId(Integer ksId) throws Exception;

}
