package com.softwarelma.ewf.client.cont;

import java.util.List;
import java.util.Map;

import com.softwarelma.epe.p1.app.EpeAppException;
import com.softwarelma.epe.p1.app.EpeAppUtils;
import com.softwarelma.ewf.client.EwfClient;
import com.softwarelma.ewf.client.comp.EwfCompBean;
import com.softwarelma.ewf.client.elem.EwfElemBean;
import com.softwarelma.ewf.client.page.EwfPageBean;
import com.vaadin.server.Responsive;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.AbstractComponent;
import com.vaadin.ui.AbstractLayout;
import com.vaadin.ui.AbstractOrderedLayout;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;

public abstract class EwfContentAbstract extends EwfContAbstract implements EwfContentInterface {

    private static final long serialVersionUID = 1L;
    private final Component component;// for a comp it is an AbstractLayout
    private final Map<String, EwfPageBean> mapPageNameAndPageBean;// could be
                                                                  // null
    private final String fileName;// could be null
    private final List<EwfContentBean> listContentBean;// could be null

    protected EwfContentAbstract(EwfClient client, UI ui, EwfElemBean elemBean, EwfContentBean contentBean)
            throws EpeAppException {
        super.init(client, ui, contentBean == null ? null : contentBean.getName());
        EpeAppUtils.checkNull("contentBean", contentBean);
        this.mapPageNameAndPageBean = elemBean == null ? null : elemBean.getMapPageNameAndPageBean();
        this.fileName = elemBean == null ? null : elemBean.getFileName();
        String compElem = null;
        String className = null;

        try {
            if (this.isComp()) {
                compElem = "layout";
                EwfCompBean compBean = client.getCompBeanNotNull(this.getName());
                EpeAppUtils.checkNull("compBean", compBean);
                this.listContentBean = compBean.getListContentBean();
                className = compBean.getClassNameLayout();
                EpeAppUtils.checkNull("className", className);
                this.component = (AbstractLayout) Class.forName(className).newInstance();
                AbstractLayout layout = (AbstractLayout) this.component;

                if (layout instanceof AbstractOrderedLayout) {
                    ((AbstractOrderedLayout) layout).setDefaultComponentAlignment(Alignment.TOP_CENTER);
                }

                layout.setWidth("100%");
                // layout.addStyleName("ewfFlexWrap");//FIXME
                // content.addComponent(layout);

                // Enable Responsive CSS selectors for the layout
                if (layout.getClass().getName().endsWith("CssLayout")) {
                    Responsive.makeResponsive(layout);
                }
            } else if (this.isElem()) {
                EpeAppUtils.checkNull("elemBean", elemBean);
                this.listContentBean = null;

                if (elemBean.getElemCustomClassName() == null) {
                    // default elem
                    compElem = "default Vaadin component";
                    className = elemBean.getComponentClassName();
                    EpeAppUtils.checkEmpty("componentClassName", className);
                    this.component = (AbstractComponent) Class.forName(className).newInstance();

                    if (this.component instanceof Label) {
                        ((Label) this.component).setContentMode(ContentMode.HTML);
                        Responsive.makeResponsive(this.component);// TODO din
                    }
                } else {
                    // custom elem
                    compElem = "custom Vaadin component";
                    className = elemBean.getElemCustomClassName();
                    EpeAppUtils.checkEmpty("elemCustomClassName", className);
                    this.component = this.getComponent();
                    EpeAppUtils.checkNull("this.component", this.component);
                }
            } else {
                throw new EpeAppException("A content should be either a comp or an elem.");
            }
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            throw new EpeAppException("Invalid " + compElem + " class name \"" + className + "\" for comp name \""
                    + this.getName() + "\".", e);
        }

        EpeAppUtils.checkNull("component", this.component);
        if (contentBean.getListStyleName() != null) {
            for (String styleName : contentBean.getListStyleName()) {
                this.component.addStyleName(styleName);
            }
        }
    }

    @Override
    public Component getComponent() throws EpeAppException {
        return component;
    }

    @Override
    public boolean isContainer() {
        return false;
    }

    @Override
    public boolean isContent() {
        return true;
    }

    protected String getFileName() {
        return fileName;
    }

    protected List<EwfContentBean> getListContentBean() {
        return listContentBean;
    }

    protected Map<String, EwfPageBean> getMapPageNameAndPageBean() {
        return mapPageNameAndPageBean;
    }

}
