package com.ruaho.studyapp.http;

import com.ruaho.studyapp.bean.Bean;
import com.ruaho.studyapp.bean.Constant;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by jinyunyang on 15/3/5.
 */
public class OutBean extends HttpResBean {
    /**_SAVEIDS_:保存成功的主键信息，多个逗号分隔*/
    public static final String SAVE_IDS = "_SAVEIDS_";
    /**_DELIDS_:删除成功的主键信息，多个逗号分隔*/
    public static final String DEL_IDS = "_DELIDS_";
    /**_OKCOUNT_:操作成功的数量*/
    public static final String DATA_COUNT = "_OKCOUNT_";
    /**_PAGE_: 分页包装标签 */
    public static final String PAGE = "_PAGE_";

    /**_ADD_:byid时是否添加模式，用于提供给byid前后端判断是否为添加模式 */
    public static final String BYID_ADD_FLAG = "_ADD_";
    /**_COLS_:query返回的标题列*/
    public static final String QUERY_COLS = "_COLS_";

    /**_DISP_: 通过dispatcher方式的JSP跳转 */
    public static final String TO_DISP = "_DISP_";
    /**_DIRE_: 通过redirect的URL跳转 */
    public static final String TO_DIRE = "_DIRE_";
    /**_HTML_: 直接通过html流进行输出 */
    public static final String TO_HTML = "_HTML_";
    /**_XML_: 直接通过xml流进行输出 */
    public static final String TO_XML = "_XML_";
    /**_LOGIN_: 跳转登录页面 */
    public static final String TO_LOGIN = "_LOGIN_";



    /**
     * sid
     */
    private static final long serialVersionUID = -8141848138888016330L;

    /**
     * 对象构造方法
     */
    public OutBean() {
        super();
    }

    /**
     * 对象构造方法
     *
     * @param outBean 数据对象
     */
    public OutBean(Bean outBean) {
        super(outBean);
    }

    /**
     * 获取执行结果列表
     * 【query、finds：会返回查询的列表数据】
     * 【delete：会返回删除成功的列表数据】
     * 【batchSave:批量保存成功的数据结果列表】
     * @return 结果列表
     */
    public List<Bean> getDataList() {
        return this.getList(Constant.RTN_DATA);
    }

    public OutBean setDataList(List<Bean> list) {
        this.set(Constant.RTN_DATA, list);
        return this;
    }

    /**
     * 获取操作成功的数据
     * @return 操作成功的数据
     */
    public Object getData() {
        return this.get(Constant.RTN_DATA);
    }

    /**
     * 设置操作成功的数据列表
     * 【query、finds:设置查询出的数据结果列表】
     * 【delete:设置删除成功的数据结果列表】
     * 【batchSave:批量保存成功的数据结果列表】
     * 【count:查询的合计数】
     * @param data 操作成功的数据
     * @return 本对象
     */
    public OutBean setData(Object data) {
        this.set(Constant.RTN_DATA, data);
        return this;
    }

    /**
     * 获取操作成功的数量
     * 【query:返回总查询记录数，不是分页内实际返回的记录数】
     * 【finds:返回总查询记录数】
     * 【delete:返回实际删除的记录数】
     * @return 操作成功的数量
     */
    public long  getCount() {
        return this.getLong(DATA_COUNT);
    }

    /**
     * 设置操作成功的数量
     * @param count 设置操作成功的数量
     * @return 本对象
     */
    public OutBean setCount(long count) {
        this.set(DATA_COUNT, count);
        return this;
    }

    /**
     * 获取删除成功的ID列表，多个ID的话之间逗号分隔，同时支持单个ID
     * 【delete、batchSave:方法用到】
     * @return 删除成功的ID列表
     */
    public String getDeleteIds() {
        return this.getStr(DEL_IDS);
    }

    /**
     * 设置删除成功的ID列表，多个ID的话之间逗号分隔，同时支持单个ID
     * 【delete、batchSave:方法用到】
     * @param ids 删除成功的ID列表
     * @return 本对象
     */
    public OutBean setDeleteIds(String ids) {
        this.set(DEL_IDS, ids);
        return this;
    }

    /**
     * 获取保存成功的ID列表，多个ID的话之间逗号分隔，同时支持单个ID
     * 【save、batchSave:保存成功的ID列表，包含添加的和修改的】
     * @return 保存成功的ID列表
     */
    public String getSaveIds() {
        return this.getStr(SAVE_IDS);
    }

    /**
     * 设置保存成功的ID列表，多个ID的话之间逗号分隔，同时支持单个ID
     * 【save、batchSave:保存成功的ID列表，包含添加的和修改的】
     * @param ids 保存成功的ID列表
     * @return 本对象
     */
    public OutBean setSaveIds(String ids) {
        this.set(SAVE_IDS, ids);
        return this;
    }

    /**
     * 获取分页信息（含总记录数等）
     * 【query:获取分页输出】
     * @return 分页信息
     */
//    public PageBean getPage() {
//        return (PageBean) this.get(PAGE);
//    }

    /**
     * 设置分页信息
     * 【query:设置自定义分页输出】
     * @param page 分页信息
     * @return 当前对象
     */
//    public OutBean setPage(PageBean page) {
//        this.set(PAGE, page);
//        return this;
//    }

    /**
     * 设置分页信息，用于快速进行页面设定，只有一个分页，一个页面显示全部数据
     * 【query:获取自定义分页输出】
     * @param showNum 实际数据数量
     * @return 当前对象
     */
//    public OutBean setPage(int showNum) {
//        PageBean page = new PageBean().setPages(1).setShowNum(showNum).setAllNum(showNum);
//        this.set(PAGE, page);
//        return this;
//    }

    /**
     * 获取标题列信息
     * @return 标题列信息
     */
    public LinkedHashMap<String, Bean> getCols() {
        return this.getLinkedMap(QUERY_COLS);
    }

    /**
     * 设置标题列信息
     * @param cols 标题列信息
     * @return 当前对象
     */
    public OutBean setCols(LinkedHashMap<String, Bean> cols) {
        this.set(QUERY_COLS, cols);
        return this;
    }

    /**
     * 获取处理结果信息
     * @return 处理结果信息
     */
    public String getMsg() {
        return this.getStr(Constant.RTN_MSG);
    }

    /**
     * 设置信息
     * @param msg 信息内容
     * @return 当前对象
     */
    public OutBean setMsg(String msg) {
        this.set(Constant.RTN_MSG, msg);
        return this;
    }

    /**
     * 设置缺省的成功信息
     * @return 当前对象
     */
    public OutBean setOk() {
        return setOk("");
    }

    /**
     * 设置成功信息
     * @param msg 信息内容，可以为空
     * @return 当前对象
     */
    public OutBean setOk(String msg) {
        this.set(Constant.RTN_MSG, Constant.RTN_MSG_OK + msg);
        return this;
    }

    /**
     * 设置缺省的警告信息
     * @return 当前对象
     */
    public OutBean setWarn() {
        return setWarn("");
    }

    /**
     * 设置警告信息
     * @param msg 信息内容，可以为空
     * @return 当前对象
     */
    public OutBean setWarn(String msg) {
        this.set(Constant.RTN_MSG, Constant.RTN_MSG_WARN + msg);
        return this;
    }

    /**
     * 设置缺省的失败信息
     * @return 当前对象
     */
    public OutBean setError() {
        return setError("");
    }

    /**
     * 设置失败信息
     * @param msg 信息内容，可以为空
     * @return 当前对象
     */
    public OutBean setError(String msg) {
        this.set(Constant.RTN_MSG, Constant.RTN_MSG_ERROR + msg);
        return this;
    }

    /**
     *
     * @return 返回不带“Error,”字符的错误信息
     */
    public String getRealErrorMsg() {
        String str = this.getMsg();

        if(str.startsWith(Constant.RTN_MSG_ERROR)) {
            return str.substring(Constant.RTN_MSG_ERROR.length());
        }

        return str;
    }

    /**
     * 由于很多方法执行完成之后没有设置OK，因此建议判断服务器端是否执行成功以是否有错误为准。
     * @return 是否执行失败。
     */
    public boolean isError() {
        return this.getStr(Constant.RTN_MSG).startsWith(Constant.RTN_MSG_ERROR) ? true : false;
    }

    /**
     * 获取执行是否成功
     * @return 是否执行成功
     */
    public boolean isOk() {
        return this.getStr(Constant.RTN_MSG).startsWith(Constant.RTN_MSG_OK) ? true : false;
    }

    /**
     * 获取执行是否有警告或者成功信息
     * @return 是否有警告或者成功信息
     */
    public boolean isOkOrWarn() {
        return this.getStr(Constant.RTN_MSG).startsWith(Constant.RTN_MSG_ERROR) ? false : true;
    }

    /**
     * 获取byid用户添加模式还是修改模式，提供给前后端的按钮进行规则判断
     * 【byid:用于获取当前的byid方法是用于空白数据的添加模式还是已有数据的修改模式】
     * @return byid添加模式标志
     */
    public boolean getByidAddFlag() {
        return this.getBoolean(BYID_ADD_FLAG);
    }

    /**
     * 设置byid用户添加模式还是修改模式，提供给前后端的按钮进行规则判断
     * 【byid:用于设置当前的byid方法是用于空白数据的添加模式还是已有数据的修改模式】
     * @param addFlag byid添加模式标志
     * @return 本对象
     */
    public OutBean setByidAddFlag(boolean addFlag) {
        this.set(BYID_ADD_FLAG, addFlag);
        return this;
    }

    /**
     * 获取需要通过dispatcher跳转的JSP或者Servlet地址URL
     * @return 需要跳转的JSP或者Servlet地址URL
     */
    public String getToDispatcher() {
        return this.getStr(TO_DISP);
    }

    /**
     * 设置需要通过dispatche跳转的JSP或者Servlet地址URL
     * @param url 需要跳转的JSP或者Servlet地址URL，从"/"根开始
     * @return 本对象
     */
    public OutBean setToDispatcher(String url) {
        this.set(TO_DISP, url);
        return this;
    }

    /**
     * 获取需要通过redirect跳转的JSP或者Servlet地址URL
     * @return 需要跳转的JSP或者Servlet地址URL
     */
    public String getToRedirect() {
        return this.getStr(TO_DIRE);
    }

    /**
     * 设置需要通过redirect跳转的JSP或者Servlet地址URL
     * @param url 需要跳转的JSP或者Servlet地址URL，从"/"根开始
     * @return 本对象
     */
    public OutBean setToRedirect(String url) {
        this.set(TO_DIRE, url);
        return this;
    }

    /**
     * 获取需要输出的Html字符串，servlet会通过流直接返回html代码
     * @return 需要输出的Html字符串
     */
    public String getToHtml() {
        return this.getStr(TO_HTML);
    }

    /**
     * 设置需要输出的Html字符串，servlet会通过流直接返回html代码
     * @param htmlContent 需要输出的Html字符串
     * @return 本对象
     */
    public OutBean setToHtml(String htmlContent) {
        this.set(TO_HTML, htmlContent);
        return this;
    }

    /**
     * 获取需要输出的xml字符串，servlet会通过流直接返回xml代码
     * @return 需要输出的xml字符串
     */
    public String getToXml() {
        return this.getStr(TO_XML);
    }

    /**
     * 设置需要输出的xml字符串，servlet会通过流直接返回xml代码
     * @param xmlContent 需要输出的xml字符串
     * @return 本对象
     */
    public OutBean setToXml(String xmlContent) {
        this.set(TO_XML, xmlContent);
        return this;
    }

    /**
     * 设置对象，支持级联设置
     * @param key   键值
     * @param obj   对象数据
     * @return this，当前Bean
     */
    public OutBean set(Object key, Object obj) {
        put(key, obj);
        return this;
    }
}
