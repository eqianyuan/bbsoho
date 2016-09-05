<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <meta name="renderer" content="webkit">
    <meta charset="utf-8">
    <title>首页 - 百百SOHO</title>
    <c:import url="common_inport.jsp"/>
    <script type="text/javascript" src="js/package/index.js"></script>
</head>
<body class="pt97">
<!-- header -->
<c:import url="head.jsp"/>
<!-- /header -->
<!-- lr-wrapper -->
<div class="lr-wrapper">
    <div class="lr-img"><img src="images/index/banner.png" alt="background-image" /></div>
    <div class="container">
        <!-- index-top -->
        <div class="index-top">
            <h2>陌生的城市 -   奇妙的邂逅 -   源源不断的收入</h2>
            <h2>一场说走就走的旅行</h2>
            <div class="search-box">
                <form action="staticPage/talents_list.jsp?menuNavigation=talentPool" method="post" class="search-form">
                    <input type="text" name="keyword" class="ipt-txt" placeholder="搜索工程师" />
                    <button type="submit" class="ipt-submit">搜索</button>
                </form>
                <p>玩味生活，边工作，边旅行，边恋爱，我在另一个城市期待与你的不期而遇...</p>
            </div>
        </div>
        <!-- /index-top -->
    </div>
</div>
<!-- /lr-wrapper -->
<!-- mainer -->
<div class="mainer pd30">
    <div class="container">
        <div class="index-title">
            <h2>我们的优势</h2>
            <p>为企业快速匹配高科技创新人才；为个人提供全国的就业舞台，旅行工作两不误。</p>
        </div>
        <!-- index-box -->
        <div class="index-box">
            <div class="advantage" id="advantage">
                <dl>
                    <dt><img src="images/index/i_01.png" alt="1" /></dt>
                    <dd>
                        <h3>信息真实</h3>
                        <p>机器初审，人工二次审核，筛选，保证信息真实有效。</p>
                    </dd>
                </dl>
                <dl>
                    <dt><img src="images/index/i_02.png" alt="2" /></dt>
                    <dd>
                        <h3>周结薪水</h3>
                        <p>周周领薪水，旅行轻松无忧，开启新的消费模式。</p>
                    </dd>
                </dl>
                <dl>
                    <dt><img src="images/index/i_03.png" alt="3" /></dt>
                    <dd>
                        <h3>保险保障</h3>
                        <p>平台提供固定城市缴纳五险一金，旅行定居两不误。</p>
                    </dd>
                </dl>
                <dl>
                    <dt><img src="images/index/i_04.png" alt="4" /></dt>
                    <dd>
                        <h3>考核机制</h3>
                        <p>保障公司和个人的利益，提供个人后期的发展空间。</p>
                    </dd>
                </dl>
                <dl>
                    <dt><img src="images/index/i_05.png" alt="5" /></dt>
                    <dd>
                        <h3>奖励机制</h3>
                        <p>个人能力提升，公司好评等，会提供适当奖励和职位晋升。</p>
                    </dd>
                </dl>
                <dl>
                    <dt><img src="images/index/i_06.png" alt="6" /></dt>
                    <dd>
                        <h3>平台担保机制</h3>
                        <p>公司提供预付金，保障个人利益，上班无后顾之忧。</p>
                    </dd>
                </dl>
            </div>
            <div class="btn-start">
                <div class="inner"><button type="button" class="ipt-button">开始体验</button></div>
            </div>
        </div>
        <!-- /index-box -->
    </div>
</div>
<!-- /mainer -->
<!-- lr-wrapper -->
<div class="lr-wrapper">
    <div class="lr-img"><img src="images/index/banner2.png" alt="background-image" /></div>
</div>
<!-- /lr-wrapper -->
<!-- mainer -->
<div class="mainer pd30">
    <div class="container">
        <div class="index-title">
            <h2>流程图</h2>
            <p>为企业快速匹配高科技创新人才；为个人提供全国的就业舞台，旅行工作两不误。</p>
        </div>
        <!-- index-box -->
        <div class="index-box">
            <div class="advantage flow-index" id="flowIndex">
                <dl>
                    <dd>
                        <h3>信息真实</h3>
                        <p>机器初审，人工二次审核，筛选，保证信息真实有效。</p>
                    </dd>
                </dl>
                <dl>
                    <dd>
                        <h3>周结薪水</h3>
                        <p>周周领薪水，旅行轻松无忧，开启新的消费模式。</p>
                    </dd>
                </dl>
                <dl>
                    <dd>
                        <h3>保险保障</h3>
                        <p>平台提供固定城市缴纳五险一金，旅行定居两不误。</p>
                    </dd>
                </dl>
            </div>
            <div class="flow-image">
                <img src="images/index/img_01.png" alt="1" />
            </div>
        </div>
        <!-- /index-box -->
    </div>
</div>
<!-- /mainer -->
<!-- mainer -->
<div class="mainer pd30">
    <div class="container">
        <div class="index-title">
            <h2>合作伙伴</h2>
            <p>8家企业的共同选择。</p>
        </div>
        <!-- index-box -->
        <div class="index-box f-link" id="fLink">
            <a href="javascript:;"><img src="images/index/link_01.png" alt="1" /></a>
            <a href="javascript:;"><img src="images/index/link_02.png" alt="2" /></a>
            <a href="javascript:;"><img src="images/index/link_03.png" alt="3" /></a>
            <a href="javascript:;"><img src="images/index/link_04.png" alt="4" /></a>
            <a href="javascript:;"><img src="images/index/link_05.png" alt="5" /></a>
            <a href="javascript:;"><img src="images/index/link_06.png" alt="6" /></a>
            <a href="javascript:;"><img src="images/index/link_07.png" alt="7" /></a>
            <a href="javascript:;"><img src="images/index/link_08.png" alt="8" /></a>
            <a href="javascript:;"><img src="images/index/link_09.png" alt="9" /></a>
            <a href="javascript:;"><img src="images/index/link_10.png" alt="10" /></a>
        </div>
        <!-- /index-box -->
    </div>
</div>
<!-- /mainer -->
<!-- footer -->
<c:import url="footer.jsp"/>
<!-- /footer -->
</body>
</html>