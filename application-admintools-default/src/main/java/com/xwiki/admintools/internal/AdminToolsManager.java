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
package com.xwiki.admintools.internal;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

import org.xwiki.component.annotation.Component;

import com.xpn.xwiki.XWikiContext;
import com.xpn.xwiki.web.XWikiRequest;
import com.xwiki.admintools.DataProvider;

/**
 * Manages the data providers.
 *
 * @version $Id$
 * @since 1.0
 */
@Component(roles = AdminToolsManager.class)
@Singleton
public class AdminToolsManager
{
    /**
     * A list of all the data providers for Admin Tools.
     */
    @Inject
    private Provider<List<DataProvider>> dataProviderProvider;

    @Inject
    private Provider<XWikiContext> xcontextProvider;

    /**
     * Merges all templates generated by the data providers.
     *
     * @return a {@link String} containing all templates builds.
     */
    public String generateData()
    {
        StringBuilder strBuilder = new StringBuilder();

        for (DataProvider dataProvider : this.dataProviderProvider.get()) {
            strBuilder.append(dataProvider.provideData());
            strBuilder.append("\n");
        }
        return strBuilder.toString();
    }

    /**
     * Extract a specific data provider template.
     *
     * @param hint {@link String} represents the data provider identifier.
     * @return a {@link String} representing a template
     */
    public Map<String, String> generateData(String hint)
    {
        for (DataProvider dataProvider : this.dataProviderProvider.get()) {
            if (dataProvider.getIdentifier().equals(hint)) {
                return dataProvider.generateJson();
            }
        }
        return null;
    }

    /**
     * Get the context path for the current XWiki request.
     *
     * @return a String representing the xwiki context path.
     */
    public String getContextPath()
    {
        XWikiContext xWikiContext = this.xcontextProvider.get();
        XWikiRequest xWikiRequest = xWikiContext.getRequest();

        return xWikiRequest.getContextPath();
    }
}
