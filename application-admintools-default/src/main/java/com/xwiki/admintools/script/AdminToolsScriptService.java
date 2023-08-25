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
package com.xwiki.admintools.script;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import org.xwiki.component.annotation.Component;
import org.xwiki.rendering.block.Block;
import org.xwiki.script.service.ScriptService;

import com.xwiki.admintools.internal.AdminToolsManager;

/**
 * Admin Tools script services.
 *
 * @version $Id$
 * @since 1.0
 */
@Component
@Named("admintools")
@Singleton
public class AdminToolsScriptService implements ScriptService
{
    /**
     * Manages the XWiki configuration info.
     */
    @Inject
    private AdminToolsManager adminToolsManager;

//    /**
//     * Function used to retrieve the configuration info json.
//     *
//     * @return the configuration info json.
//     */
//    public Map<String, String> getConfigurationDetails()
//    {
//        return this.adminToolsManager.getConfigurationDetails();
//    }
//
//    /**
//     * Function used to retrieve the security info json.
//     *
//     * @return the configuration info json.
//     */
//    public Map<String, String> getSecurityDetails()
//    {
//        return this.adminToolsManager.getSecurityDetails();
//    }

    /**
     * Function used to retrieve the security info json.
     *
     * @return the configuration info json.
     */
    public List<Block> getAllData()
    {
        return this.adminToolsManager.generateData();
    }

    /**
     * Function used to retrieve the security info json.
     *
     * @return the configuration info json.
     */
    public List<Block> getConfigData()
    {
        return this.adminToolsManager.generateConfigData();
    }
}
