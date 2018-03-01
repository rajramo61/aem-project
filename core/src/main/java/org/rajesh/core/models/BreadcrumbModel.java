package org.rajesh.core.models;

import com.day.cq.wcm.api.Page;
import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.Default;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Via;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

@Model(adaptables=SlingHttpServletRequest.class)
public class BreadcrumbModel {

    private static final String HIDE_CURRENT_PAGE = "hideCurrentPage";
    private static final String RELATIVE_PARENT = "relativeParent";
    private static final String FALSE = "false";
    private static final String HTML_EXTN = ".html";

    @Inject
    private Page currentPage;

    @Inject
    @Via("resource")
    @Named(HIDE_CURRENT_PAGE)
    @Default(values = {"false"})
    private String hideCurrentPage;

    @Inject
    @Via("resource")
    @Named(RELATIVE_PARENT)
    @Default(intValues = {1})
    private int relativeParent;

    private List<BreadcrumbPojo> breadcrumbList;

    @PostConstruct
    protected void init(){
        int currentPageDepth = currentPage.getDepth();
        int initialDepth = relativeParent + 1; //Breadcrumb should start from the next level of the main selected parent
        int difference = currentPageDepth - initialDepth;
        difference = difference > 0 ? difference : 1;
        breadcrumbList = new ArrayList<>();
        for(int level = 1; level < difference; level++){
            Page parentPage = currentPage.getParent(difference - level);
            String title = StringUtils.isNotEmpty(parentPage.getNavigationTitle())
                    ? parentPage.getNavigationTitle() :
                    StringUtils.isNotEmpty(parentPage.getTitle())
                            ? parentPage.getTitle() : parentPage.getName();
            final BreadcrumbPojo breadcrumbPojo = new BreadcrumbPojo();
            breadcrumbPojo.setPageTitle(title);
            breadcrumbPojo.setPagePath(parentPage.getPath() + HTML_EXTN);
            breadcrumbPojo.setHidePage(parentPage.getProperties().get(HIDE_CURRENT_PAGE, FALSE));
            breadcrumbList.add(breadcrumbPojo);
        }
    }

    public List<BreadcrumbPojo> getBreadcrumbList(){
        return breadcrumbList;
    }

    public boolean getHideCurrentPage(){
        return Boolean.valueOf(hideCurrentPage);
    }
}
