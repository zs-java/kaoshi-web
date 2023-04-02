package com.xzcoder.kaoshi.admin.service;

import java.math.BigDecimal;
import java.util.List;

import net.sf.json.JSONObject;

import com.xzcoder.kaoshi.common.po.PageBean;
import com.xzcoder.kaoshi.po.ExamSjDataCustom;
import com.xzcoder.kaoshi.po.SysUserCustom;
import com.xzcoder.kaoshi.vo.SjDataQueryVo;

/**
 * SjDataService
 *
 * @author <a href="https://xzcoder.com">朱帅</a>
 */
public interface SjDataService {

	/**
	 * 组合条件分页查询试卷信息集合
	 * @param sjDataQueryVo
	 * @return
	 * @throws Exception
	 */
	public List<ExamSjDataCustom> findSjDataListByVo(SjDataQueryVo sjDataQueryVo, PageBean pageBean, SysUserCustom user) throws Exception;

	/**
	 * 添加试卷信息
	 * 返回自增主键
	 * @param title
	 * @param sjClassifyId
	 * @param des
	 * @param count
	 * @param totalScore
	 * @param stInfo
	 * @param visable
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public Integer insertSjData(String title, Integer sjClassifyId, String des,
                                Integer count, BigDecimal totalScore, String stInfo, Integer visable, SysUserCustom user) throws Exception;

	/**
	 * 更新试卷信息
	 * @param sjId
	 * @param title
	 * @param sjClassifyId
	 * @param des
	 * @param count
	 * @param totalScore
	 * @param stInfo
	 * @param user
	 * @throws Exception
	 */
	public void updateSjData(Integer sjId, String title, Integer sjClassifyId, String des,
                             Integer count, BigDecimal totalScore, String stInfo, Integer visable, SysUserCustom user) throws Exception;

	/**
	 * 根据id查询试卷详细信息
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public ExamSjDataCustom getSjDataById(Integer id, SysUserCustom user) throws Exception;

	/**
	 * 根据id删除试卷信息
	 * @param id
	 * @param user
	 * @throws Exception
	 */
	public void deleteSjDataById(Integer id, SysUserCustom user) throws Exception;

	/**
	 * 根据id集合批量删除试卷信息
	 * @param ids
	 * @param user
	 * @throws Exception
	 */
	public void deleteSjDataList(Integer[] ids, SysUserCustom user) throws Exception;

	/**
	 * 根据试题信息解析出试题的详细信息json串
	 * @param gdxtObj
	 * @return
	 * @throws Exception
	 */
	public JSONObject getXzstJSONByStInfo(JSONObject gdxtObj) throws Exception;

}
