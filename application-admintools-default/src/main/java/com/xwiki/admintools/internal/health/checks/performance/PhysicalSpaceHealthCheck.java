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
package com.xwiki.admintools.internal.health.checks.performance;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import org.slf4j.Logger;
import org.xwiki.component.annotation.Component;

import com.xwiki.admintools.ServerInfo;
import com.xwiki.admintools.health.HealthCheck;
import com.xwiki.admintools.jobs.JobResult;
import com.xwiki.admintools.jobs.JobResultLevel;
import com.xwiki.admintools.internal.data.identifiers.CurrentServer;

/**
 * Implementation of {@link HealthCheck} for checking if system free space XWiki requirements.
 *
 * @version $Id$
 */
@Component
@Named(PhysicalSpaceHealthCheck.HINT)
@Singleton
public class PhysicalSpaceHealthCheck implements HealthCheck
{
    /**
     * Component identifier.
     */
    public static final String HINT = "physicalSpace";

    @Inject
    private CurrentServer currentServer;

    @Inject
    private Logger logger;

    @Override
    public JobResult check()
    {
        File diskPartition;
        ServerInfo server = currentServer.getCurrentServer();
        if (server == null) {
            if (System.getProperty("os.name").toLowerCase().contains("windows")) {
                diskPartition = new File("C:");
            } else {
                diskPartition = new File("/");
            }
        } else {
            Path xwikiPath = Paths.get(server.getXwikiCfgFolderPath());
            Path rootDrive = xwikiPath.getRoot();
            diskPartition = new File(String.valueOf(rootDrive));
        }
        long freePartitionSpace = diskPartition.getFreeSpace();
        float freeSpace = (float) freePartitionSpace / (1024 * 1024 * 1024);

        if (freeSpace > 16) {
            return new JobResult("adminTools.dashboard.healthcheck.performance.space.info",
                JobResultLevel.INFO);
        }
        logger.warn("There is not enough free space for the XWiki installation! Current free space is [{}]",
            freeSpace);
        return new JobResult("adminTools.dashboard.healthcheck.performance.space.warn",
            JobResultLevel.WARN, freeSpace, diskPartition.getAbsolutePath());
    }
}
