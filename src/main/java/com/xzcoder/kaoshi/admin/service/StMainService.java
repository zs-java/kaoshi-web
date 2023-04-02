package com.xzcoder.kaoshi.admin.service;

import com.xzcoder.kaoshi.common.po.PageBean;
import com.xzcoder.kaoshi.po.ExamStBianchengCustom;
import com.xzcoder.kaoshi.po.ExamStDanxuanCustom;
import com.xzcoder.kaoshi.po.ExamStDuoxuanCustom;
import com.xzcoder.kaoshi.po.ExamStMainCustom;
import com.xzcoder.kaoshi.po.ExamStPanduanCustom;
import com.xzcoder.kaoshi.po.ExamStTiankongCustom;
import com.xzcoder.kaoshi.po.SysUserCustom;
import com.xzcoder.kaoshi.vo.StMainQueryVo;

import java.util.List;

import net.sf.json.JSONObject;

/**
 * StMainService
 *
 * @author <a href="https://xzcoder.com">朱帅</a>
 */
public interface StMainService {

	/**
	 * 组合条件查询试题信息
	 *
	 * @param stMainQueryVo
	 * @return
	 * @throws Exception
	 */
	public List<ExamStMainCustom> findStListByStQueryVo(
            StMainQueryVo stMainQueryVo, PageBean pageBean, SysUserCustom user) throws Exception;

	/**
	 * 组合条件查询可以使用的试题信息集合
	 * 可以使用：编号不在传入的试题编号数组中
	 * @param stMainQueryVo
	 * @param pageBean
	 * @return
	 * @throws Exception
	 */
	public List<ExamStMainCustom> findStUseableByVo(StMainQueryVo stMainQueryVo, PageBean pageBean, SysUserCustom user) throws Exception;

	/**
	 * 添加单选试题 先向试题主表中添加试题信息，获取自增主键 （试题编号） 将获取到的试题编号设置到单选题详细信息中 再向单选题表中添加单选题的具体信息
	 *
	 * @param classifyId
	 * @param levelId
	 * @param knowledgeId
	 * @param title
	 * @param options
	 * @param detail
	 * @param rightKey
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public Integer insertDanxuanSt(Integer classifyId, Integer levelId,
                                   Integer knowledgeId, String title, String titleText,
                                   String options, String detail, Integer rightKey, Integer visable, SysUserCustom user)
			throws Exception;

	public Integer insertBianchengSt(Integer classifyId, Integer levelId,
                                     Integer knowledgeId, String title, String titleText, String detail, Integer visable, SysUserCustom user) throws Exception;

	/**
	 * 添加多选题
	 * 先向试题主表中添加试题基本信息
	 * 获取自增主键试题编号
	 * 再向多选题表中添加该多选试题的详细信息
	 * @param classifyId
	 * @param levelId
	 * @param knowledgeId
	 * @param title
	 * @param titleText
	 * @param options
	 * @param detail
	 * @param rightKey
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public Integer insertDuoxuanSt(Integer classifyId, Integer levelId,
                                   Integer knowledgeId, String title, String titleText,
                                   String options, String detail, Integer[] rightKeys, Integer visable, SysUserCustom user) throws Exception;

	/**
	 * 添加判断题
	 * @param classifyId
	 * @param levelId
	 * @param knowledgeId
	 * @param title
	 * @param titleText
	 * @param options
	 * @param detail
	 * @param rightKey
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public Integer insertPanduanSt(Integer classifyId, Integer levelId,
                                   Integer knowledgeId, String title, String titleText,
                                   String detail, Integer rightKey, Integer visable, SysUserCustom user) throws Exception;

	/**
	 * 添加填空题
	 * @param classifyId
	 * @param levelId
	 * @param knowledgeId
	 * @param title
	 * @param titleText
	 * @param deatil
	 * @param rightKey
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public Integer insertTiankongSt(Integer classifyId, Integer levelId,
                                    Integer knowledgeId, String title, String titleText,
                                    String detail, String rightKey, Integer visable, SysUserCustom user) throws Exception;

	/**
	 * 根据试题编号获取试题信息
	 *
	 * @param questionId
	 * @return
	 * @throws Exception
	 */
	public ExamStMainCustom getStMainById(Integer questionId) throws Exception;

	/**
	 * 根据试题编号获取单选题试题信息
	 *
	 * @param questionId
	 * @return
	 * @throws Exception
	 */
	public ExamStDanxuanCustom getStDanxuanById(Integer questionId) throws Exception;

	public ExamStBianchengCustom getStBianchengById(Integer questionId) throws Exception;

	/**
	 * 根据id获取多选题信息
	 * @param questionId
	 * @return
	 * @throws Exception
	 */
	public ExamStDuoxuanCustom getStDuoxuanById(Integer questionId) throws Exception;

	/**
	 * 根据 id获取判断题信息
	 * @param questionId
	 * @return
	 * @throws Exception
	 */
	public ExamStPanduanCustom getStPanduanById(Integer questionId) throws Exception;

	/**
	 * 根据id获取填空题信息
	 * @param questionid
	 * @return
	 * @throws Exception
	 */
	public ExamStTiankongCustom getStTiankongById(Integer questionId) throws Exception;

	/**
	 * 根据试题编号 更新单选题信息
	 * @param questionId
	 * @throws Exception
	 */
	public void updateStDanxuanById(Integer questionId, Integer classifyId, Integer levelId,
                                    Integer knowledgeId, String title, String titleText,
                                    String options, String detail, Integer rightKey, Integer visable, SysUserCustom user) throws Exception;

	/**
	 * 根据试题编号更新多选题信息
	 * @param questionId
	 * @throws Exception
	 */
	public void updateStDuoxuanById(Integer questionId, Integer classifyId, Integer levelId,
                                    Integer knowledgeId, String title, String titleText,
                                    String options, String detail, Integer[] rightKey, Integer visable, SysUserCustom user) throws Exception;

	public void updateStBianchengById(Integer questionId, Integer classifyId, Integer levelId,
                                      Integer knowledgeId, String title, String titleText, String detail, Integer visable, SysUserCustom user) throws Exception;

	/**
	 * 根据 试题编号更新判断题信息
	 * @param questionId
	 * @param classifyId
	 * @param levelId
	 * @param knowledgeId
	 * @param title
	 * @param titleText
	 * @param options
	 * @param detail
	 * @param rightKey
	 * @param user
	 * @throws Exception
	 */
	public void updateStPanduanById(Integer questionId, Integer classifyId, Integer levelId,
                                    Integer knowledgeId, String title, String titleText,
                                    String detail, Integer rightKey, Integer visable, SysUserCustom user) throws Exception;

	/**
	 * 更新填空题信息
	 * @param questionId
	 * @param classifyId
	 * @param levelId
	 * @param knowledgeId
	 * @param title
	 * @param titleText
	 * @param detail
	 * @param rightKey
	 * @param user
	 * @throws Exception
	 */
	public void updateStTiankongById(Integer questionId, Integer classifyId, Integer levelId,
                                     Integer knowledgeId, String title, String titleText,
                                     String detail, String rightKey, Integer visable, SysUserCustom user) throws Exception;

	/**
	 * 删除试题
	 * @param questionId
	 * @throws Exception
	 */
	public void deleteStById(Integer questionId) throws Exception;

	/**
	 * 批量删除试题
	 * @param ids
	 * @throws Exception
	 */
	public void deleteStList(Integer[] ids) throws Exception;

	public JSONObject getStJsonById(Integer qsnId) throws Exception;

	public List<ExamStMainCustom> findStListByIds(StMainQueryVo vo) throws Exception;
}
