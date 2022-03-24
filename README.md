# TextView中识别网页链接，并可做点击链接操作

　实现思路：SpannableString 设置 ClickableSpan

    1、识别网页链接，用正则表达式去匹配。
    2、继承ClickableSpan，重写相关方法