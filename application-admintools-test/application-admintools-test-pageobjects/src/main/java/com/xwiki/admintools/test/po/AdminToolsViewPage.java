/*
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package com.xwiki.admintools.test.po;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.xwiki.test.ui.po.ViewPage;

public class AdminToolsViewPage extends ViewPage
{
    public List<WebElement> dashboardElements =
        getDriver().findElements(By.xpath("//div[contains(@class, 'adminToolsDashboardItem')]"));

    public List<WebElement> warningElements =
        getDriver().findElements(By.xpath("//div[contains(@class, 'warningmessage')]"));

    @FindBy(xpath = "//a[@data-target = '#configurationViewLastNLinesModal']")
    public WebElement backendLogsHyperlink;

    @FindBy(xpath = "//div[@id = 'adminToolsBackendSection'] /ul")
    public WebElement backendContent;

    @FindBy(xpath = "//div[@id = 'adminToolsFilesSection'] /ul")
    public WebElement filesContent;

    public List<WebElement> getDashboardElements()
    {
        return dashboardElements;
    }

    public List<WebElement> getWarningElements()
    {
        return warningElements;
    }

    public WebElement getBackendContent()
    {
        return backendContent;
    }

    public List<WebElement> getBackendErrorMessages()
    {
        return backendContent.findElements(By.className("warningmessage"));
    }

    public String getBackendText()
    {
        return backendContent.getText();
    }

    public WebElement getBackendLogsHyperlink()
    {
        return backendLogsHyperlink;
    }

    public WebElement getFilesArchiveHyperlink()
    {
        return filesContent.findElement(By.cssSelector("a[href='#downloadFilesModal'"));
    }

    public WebElement getPropertiesHyperlink()
    {
        return filesContent.findElement(By.id("filesProperties"));
    }

    public WebElement getConfigurationHyperlink()
    {
        return filesContent.findElement(By.id("filesConfig"));
    }
}