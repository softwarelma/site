package com.softwarelma.ewf.client.page;

import java.util.ArrayList;
import java.util.List;

import com.softwarelma.epe.p1.app.EpeAppException;
import com.softwarelma.epe.p1.app.EpeAppUtils;
import com.softwarelma.ewf.client.EwfClient;
import com.softwarelma.ewf.client.comp.EwfCompDefault;
import com.softwarelma.ewf.client.comp.EwfCompInterface;
import com.softwarelma.ewf.client.cont.EwfContainerAbstract;
import com.softwarelma.ewf.client.cont.EwfContentBean;
import com.vaadin.ui.UI;

public abstract class EwfPageAbstract extends EwfContainerAbstract implements EwfPageInterface {

    private static final long serialVersionUID = 1L;
    private EwfCompInterface comp;

    protected EwfPageAbstract() {
    }

    public void init(EwfClient client, UI ui, String name) throws EpeAppException {
        super.init(client, ui, name);
        EwfPageBean pageBean = client.getPageBeanNotNull(name);
        EpeAppUtils.checkNull("pageBean", pageBean);
        String compName = pageBean.getCompName();
        EpeAppUtils.checkNull("compName", compName);
        EwfContentBean contentBean = new EwfContentBean();
        contentBean.setComp(true);
        contentBean.setName(compName);
        List<String> listStyleName = new ArrayList<>();
        listStyleName.add("backColorGrey");// FIXME
        contentBean.setListStyleName(listStyleName);
        contentBean.setElemCustomClassName(null);// only for elems
        this.comp = new EwfCompDefault(client, ui, compName, contentBean);
    }

    @Override
    public EwfCompInterface getComp() {
        return comp;
    }

    @Override
    public boolean isPage() {
        return true;
    }

    @Override
    public boolean isComp() {
        return false;
    }

    @Override
    public boolean isElem() {
        return false;
    }

}
