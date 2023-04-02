package com.xzcoder.kaoshi.admin.mapper;

import java.util.Date;
import java.util.List;

import com.xzcoder.kaoshi.vo.KsDataQueryVo;
import org.apache.ibatis.annotations.Param;

import com.xzcoder.kaoshi.po.ExamKsDataCustom;
import com.xzcoder.kaoshi.po.ExamKsMonitor;
import com.xzcoder.kaoshi.vo.KsMonitorQueryVo;

/**
 * KsDataMapper
 *
 * @author <a href="https://xzcoder.com">朱帅</a>
 */
public interface KsDataMapper {

	/**
	 * 组合条件分页查询考试信息
	 * @param ksDataQueryVo
	 * @return
	 * @throws Exception
	 */
	public List<ExamKsDataCustom> findKsDataListByVo(KsDataQueryVo ksDataQueryVo) throws Exception;

	/**
	 * 组合条件查询匹配的考试数量
	 * @param ksDataQueryVo
	 * @return
	 * @throws Exception
	 */
	public Integer getKsDataCountByVo(KsDataQueryVo ksDataQueryVo) throws Exception;

	/**
	 * 添加试卷信息
	 * @param ksDataCustom
	 * @throws Exception
	 */
	public void insertKsData(ExamKsDataCustom ksDataCustom) throws Exception;

	/**
	 * 根据id查询考试信息
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public ExamKsDataCustom getExamKsDataById(Integer id) throws Exception;

	/**
	 * 根据uuid查询考试信息
	 * @param uuid
	 * @return
	 * @throws Exception
	 */
	public ExamKsDataCustom getExamKsDataByUuid(String uuid) throws Exception;

	/**
	 * 更新考试信息
	 * @param ksDataCustom
	 * @throws Exception
	 */
	public void updateKsData(ExamKsDataCustom ksDataCustom) throws Exception;

	/**
	 * 根据id删除考试信息
	 * @param ksDataCustom
	 * @throws Exception
	 */
	public void deleteKsData(@Param("ksId") Integer ksId,
                             @Param("updUser") String updUser,
                             @Param("updDate") Date updDate) throws Exception;

	/**
	 * 获取所有考试的名称和编号的集合
	 * @return
	 * @throws Exception
	 */
	public List<ExamKsMonitor> getAllExamIdAndNameList() throws Exception;

	/**
	 * 组合条件查询考试监控信息总数
	 * @param ksMonitorQueryVo
	 * @return
	 * @throws Exception
	 */
	public Integer getKsMonitorCountByVo(KsMonitorQueryVo ksMonitorQueryVo) throws Exception;

	/**
	 * 组合条件分页查询考试监控信息集合
	 * @param ksMonitorQueryVo
	 * @return
	 * @throws Exception
	 */
	public List<ExamKsMonitor> findKsMonitorListByVo(KsMonitorQueryVo ksMonitorQueryVo) throws Exception;

}
