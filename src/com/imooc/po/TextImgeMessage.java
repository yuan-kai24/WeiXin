package com.imooc.po;

import java.util.List;

public class TextImgeMessage extends BaseMessage{
    private int ArticleCount ;
    private List<TextImgeNews> Articles;

    public int getArticleCount() {
        return ArticleCount;
    }

    public void setArticleCount(int articleCount) {
        ArticleCount = articleCount;
    }

    public List<TextImgeNews> getArticles() {
        return Articles;
    }

    public void setArticles(List<TextImgeNews> articles) {
        Articles = articles;
    }
}