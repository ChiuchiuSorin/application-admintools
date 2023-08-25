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

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Provider;
import javax.inject.Singleton;

import org.xwiki.component.annotation.Component;

/**
 * Manage existing instances. or admin tools manager
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

    /**
     * Merges all the templates.
     *
     * @return a string containing all templates.
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
     * Extract a specific template.
     *
     * @param hint represents the data provider identifier.
     * @return a String representing a template
     */
    public String generateData(String hint)
    {
        StringBuilder strBuilder = new StringBuilder();

        for (DataProvider dataProvider : this.dataProviderProvider.get()) {
            if (dataProvider.getIdentifier().equals(hint)) {
                strBuilder.append(dataProvider.provideData());
                return strBuilder.toString();
            }
        }
        return null;
    }
}
