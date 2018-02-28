package org.rajesh.core.models;

public class BreadcrumbPojo {
    private String pagePath;
    private String pageTitle;
    private String hidePage;

    public String getPagePath() {
        return pagePath;
    }

    public void setPagePath(String pagePath) {
        this.pagePath = pagePath;
    }

    public String getPageTitle() {
        return pageTitle;
    }

    public void setPageTitle(String pageTitle) {
        this.pageTitle = pageTitle;
    }

    public String getHidePage() {
        return hidePage;
    }

    public void setHidePage(String hidePage) {
        this.hidePage = hidePage;
    }

    @Override
    public String toString() {
        return "BreadcrumbPojo{" +
                "pagePath='" + pagePath + '\'' +
                ", pageTitle='" + pageTitle + '\'' +
                ", hidePage='" + hidePage + '\'' +
                '}';
    }
}
