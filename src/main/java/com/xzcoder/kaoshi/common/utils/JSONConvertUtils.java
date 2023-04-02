package com.xzcoder.kaoshi.common.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.xzcoder.kaoshi.po.ExamStClassifyCustom;
import com.xzcoder.kaoshi.po.SysGroupCustom;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * JSONConvertUtils
 *
 * @author <a href="https://xzcoder.com">朱帅</a>
 */
public class JSONConvertUtils {

    public static String singleProptiesToJSON(Object key, Object value) throws Exception {
        return "{ \"" + key.toString() + "\":\"" + value.toString() + "\"}";
    }

    public static String getStatusAndInfoJson(String status, Object info) {
        JSONObject json = new JSONObject();
        json.put("msg", status);
        if (info != null) {
            json.put("info", info);
        }
        return json.toString();
    }


    public static String groupsToJSON(List<SysGroupCustom> groupList) throws Exception {
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        for (SysGroupCustom group : groupList) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("id", group.getGroupId());
            map.put("pId", group.getGroupPid());
            map.put("name", group.getGroupName());
            map.put("open", true);
            result.add(map);
        }
        JSONArray jsonArr = JSONArray.fromObject(result);
        return jsonArr.toString();
    }


    public static String groupsTreeToJSON(SysGroupCustom groupTree) throws Exception {
        List<SysGroupCustom> groups = new ArrayList<>();
        groups.add(groupTree);
        return groupsTreeToJSON(groups);
    }

    private static String groupsTreeToJSON(List<SysGroupCustom> groups) throws Exception {
        StringBuffer json = new StringBuffer("[");
        for (int i = 0; i < groups.size(); i++) {
            SysGroupCustom group = groups.get(i);
            json.append("{");
            json.append(String.format("\"id\":%d,\n", group.getGroupId()));
            json.append(String.format("\"text\":\"%s\"", group.getGroupName()));

            if (group.getChildGroups() != null && group.getChildGroups().size() > 0) {
                json.append(",\n\"state\":\"open\",\n");
                json.append("\"children\":" + groupsTreeToJSON(group.getChildGroups()));
            }
            json.append("}");
            if (i != (groups.size() - 1)) {
                json.append(",");
            }
        }
        json.append("]");
        return json.toString();
    }

    public static String stClassifyTreeToJSON(List<ExamStClassifyCustom> stcs) throws Exception {
        StringBuffer json = new StringBuffer("[");
        for (int i = 0; i < stcs.size(); i++) {
            ExamStClassifyCustom stc = stcs.get(i);
            json.append("{");
            json.append(String.format("\"id\":%d,\n", stc.getClassifyId()));
            json.append(String.format("\"text\":\"%s\"", stc.getName()));

            if (stc.getChildStClassifys() != null && stc.getChildStClassifys().size() > 0) {
                json.append(",\n\"state\":\"open\",\n");
                json.append("\"children\":" + stClassifyTreeToJSON(stc.getChildStClassifys()));
            }
            json.append("}");
            if (i != (stcs.size() - 1)) {
                json.append(",");
            }
        }
        json.append("]");
        return json.toString();
    }

}
