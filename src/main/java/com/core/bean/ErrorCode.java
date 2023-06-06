package com.core.bean;


import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.Field;

/**
 * @author ly
 * @date 2019/5/6 17:26
 * description: 响应码和msg
 */
public enum ErrorCode {
    /**
     * 公用模块(00) 100001->版本1，00表示公用模块，001错误码编号
     */
    @ApiModelProperty(notes = "文件上传失败")
    FILE_UPLOAD_FAILED(100001),
    @ApiModelProperty(notes = "文件md5校验失败")
    FILE_MD5_FAILED(100002),


    /**
     * 用户模块(01) 101001->版本1，01表示用户模块，001错误码编号
     */
    @ApiModelProperty(notes = "用户名格式错误")
    USERNAME_PATTERN_ERROR(101001),
    @ApiModelProperty(notes = "密码格式错误")
    PASSWORD_PATTERN_ERROR(101002),

    @ApiModelProperty(notes = "该账号不存在，如需开通请与我们联系")
    USERNAME_NOT_FOUND(101003),
    @ApiModelProperty(notes = "密码错误")
    PASSWORD_ERROR(101004),
    @ApiModelProperty(notes = "邮箱格式错误")
    EMAIL_PATTERN_ERROR(101005),
    @ApiModelProperty(notes = "手机号无效")
    PHONE_PATTERN_ERROR(101006),
    @ApiModelProperty(notes = "用户名已经存在")
    USERNAME_ALREADY_EXIST(101007),
    @ApiModelProperty(notes = "验证码无效")
    VERIFY_ERROR(101008),
    @ApiModelProperty(notes = "验证码发送失败请重试")
    SEND_VERIFY_FAIL(101009),
    @ApiModelProperty(notes = "当前用户获取验证码次数到达上限溢出")
    GET_VERIFY_NUM_SPILL(101010),
    @ApiModelProperty(notes = "此用户正在申请中,请等待审核")
    USER_APPLYING(101011),
    @ApiModelProperty(notes = "此账号未获得访问权限")
    USER_NOT_AUTHORITY(101012),
    @ApiModelProperty(notes = "申请用户不存在")
    NOT_APPLY_USER(101013),

    @ApiModelProperty(notes = "账号不可用，请联系贵司用户管理员或我们处理")
    NOT_STATE_USER(101014),
    @ApiModelProperty(notes = "账号已过期")
    OVERDUE_USER(101015),
    @ApiModelProperty(notes = "账号审核不通过，请联系贵司用户管理员或我们处理")
    AUDIT_NOT_THROUGH_USER(101016),
    @ApiModelProperty(notes = "账号审核中")
    NOT_AUDIT_USER(101017),
    @ApiModelProperty(notes = "账号不存在,请先申请体验")
    DELETE_USER(101018),
    @ApiModelProperty(notes = "未到有效期，暂不能登录")
    EXPIRY_DATE_USER(101019),

    @ApiModelProperty(notes = "手机号重复，请联系贵司用户管理员或我们处理")
    REPEAT_PHONE_USER(101020),


    /**
     * 前端判断code，如需修改，记得调整
     */
    @ApiModelProperty(notes = "移动端的账户，需装平台账户")
    CONVERSION_PLATFORM_USER(101021),

    /**
     * 前端判断code，如需修改，记得调整
     */
    @ApiModelProperty(notes = "异地登录")
    ANOTHER_PLACE_LOGIN(101022),

    /**
     * 前端判断code，如需修改，记得调整
     */
    @ApiModelProperty(notes = "该手机号已注册，直接登录")
    PHONE_ALREADY_EXIST(101023),

    @ApiModelProperty(notes = "该用户已过期，如需续期请与我们联系")
    PHONE_IS_LOSS_OF(101024),

    @ApiModelProperty(notes = "移动端注册-该手机号已注册，请直接登录 登录-账号不存在")
    PHONE_IS_USE(101025),

    @ApiModelProperty(notes = "【验证码】不可留空，请您输入验证码。")
    VERIFY_CODE_NULL(101026),

    /**
     * 权限相关
     */
    @ApiModelProperty(notes = "token过期")
    TOKEN_EXPIRED(102001),
    @ApiModelProperty(notes = "token无效")
    TOKEN_INVALID(102002),

    /**
     * 宏观模块
     */
    @ApiModelProperty(notes = "表已经存在")
    TABLE_IS_EXIST(103001),
    @ApiModelProperty(notes = "指定指标为空，无法查数建表")
    INDEX_IS_NULL(103002),
    @ApiModelProperty(notes = "表删除失败")
    DROP_TABLE_FAIL(103003),
    @ApiModelProperty(notes = "建表失败")
    CREATE_TABLE_FAIL(103004),
    @ApiModelProperty(notes = "插入数据失败")
    INSERT_DATA_FAIL(103005),
    @ApiModelProperty(notes = "表名含有不可使用的特殊字符")
    TABLE_NAME_TYPE_ERROR(103006),
    @ApiModelProperty(notes = "指标提数为空")
    INDEX_DATA_IS_NULL(103007),

    /**
     * 土地模块
     */
    @ApiModelProperty(notes = "当前状态无法修改")
    NOT_UPDATE(109001),
    @ApiModelProperty(notes = "无法反复提交")
    NOT_REPETITION_SUBMIT(109002),
    @ApiModelProperty(notes = "上传的空文件或者文件格式错误")
    NOT_NULL_FILE(109003),
    @ApiModelProperty(notes = "文件格式错误(必须是.xlsx格式)")
    ERROR_FILE_FORMAT(109004),
    @ApiModelProperty(notes = "没有此土地匹配记录")
    NOT_LAND_MATCH_COUNT(109005),
    @ApiModelProperty(notes = "时间格式错误请用")
    TIME_FORMAT_ERROR(109006),
    @ApiModelProperty(notes = "土地记录不完整")
    LAND_NOT_FULL(109007),
    @ApiModelProperty(notes = "文件生成失败")
    CREATE_FILE_FAIL(109008),
    @ApiModelProperty(notes = "文件读取失败")
    READ_FILE_FAIL(109010),
    @ApiModelProperty(notes = "省市请确认到区")
    PRO_CITY_AREA(109011),
    @ApiModelProperty(notes = "查询组名称重复，请输入其他名称")
    NAME_REPEAT(109012),
    @ApiModelProperty(notes = "查询组已达上限，请删除部分查询组再保存")
    NUMBER_CEILING(109013),

    /**
     * 工单模块
     */
    @ApiModelProperty(notes = "不为空")
     NOT_NULL(110001),
    @ApiModelProperty(notes = "当前用户不是审核者,或者以作废,审核不通过")
     NOT_SUBMIT(110002),
    @ApiModelProperty(notes = "当前用户无法查看")
    NOT_PERMISSION_LOOK(110003),
    @ApiModelProperty(notes = "没有记录")
    NOT_COUNT(110004),
    @ApiModelProperty(notes = "没有权限撤销")
    NOT_PERMISSION_OVER(110005),
    @ApiModelProperty(notes = "参数不能都为空")
    ALL_PARAM_NOT_NULL(110006),
    @ApiModelProperty(notes = "事务工单需要配流程")
    TRANSACTION_NEED_FLOW(110007),
    @ApiModelProperty(notes = "当前模块没有配置审核流程无法提交")
    NOT_AUDIT_FLOW(110008),
    @ApiModelProperty(notes = "当前流程存在工单未完成")
    NOT_UPDATE_TYPE(110009),

    /**
     * 数据录入与审核模块(20)
     */
    @ApiModelProperty(notes = "导入错误")
    IMPORT_ERROR(120001),
    @ApiModelProperty(notes = "请求错误")
    REQUEST_ERROR(120002),
    @ApiModelProperty(notes = "数据单元格的参数格式错误，请填写数字格式")
    NUM_FORMAT_ERROR(120003),
    @ApiModelProperty(notes = "未配置审核流程，请联系管理员配置该子版块的审核流程")
    NO_FLOW_ERROR(120004),
    @ApiModelProperty(notes = "无此工单")
    NO_ORDER_ERROR(120005),


    /**
     * 公共模块
     */
    @ApiModelProperty(notes = "没有此表")
    NOT_TABLE(130100),
    @ApiModelProperty(notes = "土地区域维度数据异常")
    LAND_AREA_IS_NULL(130101),

    /**
     * 数据调度模块
     */
    SQL_EXCEPTION(140001),
    NOT_MATEDATA(140002),

    /**
     * 数据查询模块
     */
    @ApiModelProperty(notes = "sql 异常")
    SQL_EXCEPTION_Search(150001),
    @ApiModelProperty(notes = "只允许select操作")
    ONLY_SELECT(150002),
    @ApiModelProperty(notes = "记录条数不能大于10000")
    MAX_SIZE_LIMIT(150003),
    NO_RESULT(150004),
    @ApiModelProperty(notes = "查询结果为空")
    RESULT_IS_NULL(150005),

    /**
     * 房企模块
     */
    @ApiModelProperty(notes = "已存在此记录,确认是否需要更新文件")
    EXIST_FILE_YES_UPDATE(160001),
    @ApiModelProperty(notes = "没有文件记录")
    FILE_NOT_COUNT(160002),
    @ApiModelProperty(notes = "找不到此记录")
    SELECT_FAIL(160003),
    @ApiModelProperty(notes = "文件名不规范")
    FILE_NAMW_ERROR(160004),

    /**
     * 金融模块
     */
    @ApiModelProperty(notes = "文件为空")
    NOT_FILE(170001),
    @ApiModelProperty(notes = "数据源无法连接")
    HOST_NOT_CONNECT(170002),
    @ApiModelProperty(notes = "表不存在")
    TABLE_NOT_EXIST(170003),
    @ApiModelProperty(notes = "excel解析失败")
    EXCEL_TYPE_ERROR(170004),
    @ApiModelProperty(notes = "sql语法错误")
    FINANCE_SQL_EXCEPTION(170005),
    @ApiModelProperty(notes = "文件元数据缺失")
    EXCEL_DATAMETE_MISS(17006),

    /**
     * 洞察页面
     */
    @ApiModelProperty(notes = "顶级和二级不能新增删除")
    TOP_TWO_NOT_ADD_DROP(180001),
    @ApiModelProperty(notes = "帆软url结构错误")
    FAN_RUAN_URL_ERROR(180002),
    @ApiModelProperty(notes = "土地统计请加入行表头")
    LAND_NEED_ROW(180003),
    @ApiModelProperty(notes = "当前指标全为空，无法成为表头")
    NOW_FIELD_IS_NULL(180004),


    /**
     * 项目分析
     */
    @ApiModelProperty(notes = "自定义项目必须指定定位区域")
    ZDYXM_MUST_LOCATION(190001),
    @ApiModelProperty(notes = "非默认月份请指定时间区间")
    NOT_DEFAULT_NEED_TIME(190002),
    @ApiModelProperty(notes = "没有此记录或者无法定位区域")
    NOT_NOW_RECORD(190003),
    /**
     * 通用模块
     */
    @ApiModelProperty(notes = "未授权")
    UNAUTHORIZED(401),

    @ApiModelProperty(notes = "失败")
    FAIL(-1),
//    @ApiModelProperty(notes = "Excel没有数据")
//    EXCEL_NOT_DATA(700000),

/*   一、数据文件为空提示：
   上传失败，数据文件为空。
            
   二、必填项为空提示：
   上传失败，必填项为空。
            
   三、其他上传失败提示：
   上传失败，失败原因有三种：
           （1）未按模板要求的格式填写待评估房屋信息；
           （2）上传的数据文件与模板不一致。
   请检查上传数据文件是否符合要求。
Excel没有数据*/
@ApiModelProperty(notes = "Excel没有数据")
    EXCEL_DATA_ERROR(700000),
    @ApiModelProperty(notes = "Excel必填项未填")
    EXCEL_DATA_MUST(700001),
    @ApiModelProperty(notes = "Excel模板错误等")
    EXCEL_DATA_MODEL_ERROR(700002),

    @ApiModelProperty(notes = "按钮没有获得授权")
    BUTTON_NO_PERMISSION(2021001),

    @ApiModelProperty(notes = "成功")
    SUCCESS(0),
    @ApiModelProperty(notes = "创建审批待办失败")
    CREATE_PERMISSION_ERROR(202104301),
    @ApiModelProperty(notes = "查询审批待办失败")
    QUERY_PERMISSION_ERROR(202104302),
    @ApiModelProperty(notes = "缺少参数")
    PARAM_IS_NULL(202104303),
    /**
     * 数据重复
     */
    @ApiModelProperty(notes = "数据重复")
    IS_DUPLICATE(20210520),

    /**
     * 经度或纬度为空
     */
    @ApiModelProperty(notes="经度或纬度为空")
    LGN_LAT_IS_NULL(2021061701),

    /**
     * 保存失败,系统公告时间冲突
     */
    @ApiModelProperty(notes="保存失败,系统公告时间冲突")
    SAVE_ERROR_ANNOUNCEMENT_DUPLICATE(2021070901),

    /**
     * 保存失败,系统公告标题冲突
     */
    @ApiModelProperty(notes="保存失败,系统公告标题冲突")
    SAVE_ERROR_TITLE_DUPLICATE(2021070902),
    /**
     * 保存失败,系统公告开始时间不能晚于结束时间
     */
    @ApiModelProperty(notes="保存失败,系统公告开始时间不能晚于结束时间")
    SAVE_ERROR_WRONG_TIME(2021070903),

    /**
     * 已经存在数据平台账号
     */
    @ApiModelProperty(notes="已经存在数据平台账号")
    DATA_USER_ALREADY_EXIST(2021072601),

    /**
     * 手机号码已被注册
     */
    @ApiModelProperty(notes="手机号码已被注册")
    PHONE_USER_ALREADY_EXIST(202107301),

    /**
     * 用户可授权账号数不足
     */
    @ApiModelProperty(notes="用户可授权账号数不足")
    DATA_USER_ACCOUNT_OVERFLOW(2021072801),

    /**
     * 解析EXCEL出错，单元格不能为空
     */
    @ApiModelProperty(notes="解析EXCEL出错，单元格不能为空")
    ANALYZE_EXCEL_CELL_CANNOT_NULL(2021080601),

    /**
     * 解析excel校验失败
     */
    @ApiModelProperty(notes="解析excel校验失败")
    ANALYZE_EXCEL_ERROR(2021081001),

    /**
     *该此账号未赋予权限，请联系管理员
     */
    @ApiModelProperty(notes="此账号未赋予权限，请联系管理员")
    CUSTOMER_NO_ROLE(2021083101),

    /**
     * 该用户不可用，请与我们联系
     */
    @ApiModelProperty(notes="该用户不可用，请与我们联系")
    USER_NO_CUSTOMERS(2021093102),
    /**
     * 该用户不可用，请与我们联系
     */
    @ApiModelProperty(notes="该用户不可用，请与我们联系")
    USER_NO_APPLY(2021083103),

    /**
     * 该用户未到使用时间，如需提前请与我们联系
     */
    @ApiModelProperty(notes="该用户未到使用时间，如需提前请与我们联系")
    USER_CANNOT_USE_YET(2021083104),
    /**
     * 该用户已到期，如需续期请与我们联系
     */
    @ApiModelProperty(notes="该用户已到期，如需续期请与我们联系")
    USER_OUT_OF_DATE(2021083105),
    /**
     * 此账号没有审核通过，请向系统管理员申请
     */
    @ApiModelProperty(notes="此账号没有审核通过，请向系统管理员申请")
    USER_NOT_AUDIT(2021083106),

    /**
     * 该账号不可用，请联系我们或贵司用户管理员
     */
    @ApiModelProperty(notes="该账号不可用，请联系我们或贵司用户管理员",required = true)
    USER_RESIGN(2021083107),

    /**
     * 该账号不可用，请联系我们或贵司用户管理员
     */
    @ApiModelProperty(notes="账号不存在，请先申请体验",required = true)
    MOBILE_USER_NOT_APPLY(2022112101);


    @Setter
    @Getter
    private int code;

    ErrorCode(int code) {
        this.code = code;
    }

    /**
     * 输出errorCode.json
     * @param args
     */
    public static void main(String[] args) {
       System.out.println("{");
        for (Field field: ErrorCode.class.getDeclaredFields()) {
            ApiModelProperty apiModelProperty = field.getAnnotation(ApiModelProperty.class);
            if (apiModelProperty!=null){
                System.out.println("\""+ErrorCode.valueOf(field.getName()).getCode()+"\":"+"\""+apiModelProperty.notes()+"\",");
            }
        }
        System.out.println("}");
    }
}
