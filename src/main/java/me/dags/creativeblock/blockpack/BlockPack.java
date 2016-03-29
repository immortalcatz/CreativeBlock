/*
 * The MIT License (MIT)
 *
 * Copyright (c) dags <https://www.dags.me>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package me.dags.creativeblock.blockpack;

import com.google.common.eventbus.EventBus;
import me.dags.creativeblock.CreativeBlock;
import me.dags.creativeblock.definition.BlockDefinition;
import me.dags.creativeblock.util.LogUtil;
import net.minecraftforge.fml.common.LoadController;
import net.minecraftforge.fml.common.MetadataCollection;
import net.minecraftforge.fml.common.ModContainer;
import net.minecraftforge.fml.common.ModMetadata;
import net.minecraftforge.fml.common.versioning.ArtifactVersion;
import net.minecraftforge.fml.common.versioning.VersionRange;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.security.cert.Certificate;
import java.util.*;

/**
 * @author dags <dags@dags.me>
 */

public abstract class BlockPack implements ModContainer, Versioned<BlockPack>
{
    private final File packFile;
    private final PackInfo packInfo;

    protected BlockPack(File file)
    {
        packFile = file;
        packInfo =  PackInfo.from(file.getName());
    }

    public String getTabName(String definitionsPath, String fileParentPath)
    {
        if (definitionsPath.length() >= fileParentPath.length())
        {
            return "General";
        }
        return StringUtils.capitalize(fileParentPath.substring(definitionsPath.length() + 1));
    }

    @Override
    public String toString()
    {
        return packInfo.toString();
    }

    @Override
    public BlockPack latest(BlockPack compareTo)
    {
        PackInfo latest = packInfo.latest(compareTo.packInfo);
        return latest == packInfo ? this : compareTo;
    }

    @Override
    public int compareTo(BlockPack compareTo)
    {
        return packInfo.compareTo(compareTo.packInfo);
    }

    @Override
    public String getModId()
    {
        return packInfo.name;
    }

    @Override
    public String getName()
    {
        return packInfo.toString();
    }

    @Override
    public String getVersion()
    {
        return packInfo.friendlyVersion;
    }

    @Override
    public File getSource()
    {
        return packFile;
    }

    @Override
    public ModMetadata getMetadata()
    {
        return null;
    }

    @Override
    public void bindMetadata(MetadataCollection mc)
    {

    }

    @Override
    public void setEnabledState(boolean enabled)
    {

    }

    @Override
    public Set<ArtifactVersion> getRequirements()
    {
        return null;
    }

    @Override
    public List<ArtifactVersion> getDependencies()
    {
        return null;
    }

    @Override
    public List<ArtifactVersion> getDependants()
    {
        return null;
    }

    @Override
    public String getSortingRules()
    {
        return null;
    }

    @Override
    public boolean registerBus(EventBus bus, LoadController controller)
    {
        return false;
    }

    @Override
    public boolean matches(Object mod)
    {
        return false;
    }

    @Override
    public Object getMod()
    {
        return null;
    }

    @Override
    public ArtifactVersion getProcessedVersion()
    {
        return null;
    }

    @Override
    public boolean isImmutable()
    {
        return false;
    }

    @Override
    public String getDisplayVersion()
    {
        return null;
    }

    @Override
    public VersionRange acceptableMinecraftVersionRange()
    {
        return null;
    }

    @Override
    public Certificate getSigningCertificate()
    {
        return null;
    }

    @Override
    public Map<String, String> getCustomModProperties()
    {
        return null;
    }

    @Override
    public Map<String, String> getSharedModDescriptor()
    {
        return null;
    }

    @Override
    public Disableable canBeDisabled()
    {
        return null;
    }

    @Override
    public String getGuiClassName()
    {
        return null;
    }

    @Override
    public List<String> getOwnedPackages()
    {
        return null;
    }

    @Override
    public boolean shouldLoadInEnvironment()
    {
        return false;
    }

    @Override
    public URL getUpdateUrl()
    {
        return null;
    }

    public abstract List<BlockDefinition> getDefinitions();

    public abstract void copyServerTextures() throws IOException;

    public static List<BlockPack> getBlockPacks(CreativeBlock creativeBlock, File blockpackDir)
    {
        LogUtil.blockpack(BlockPack.class, "Scanning {} for blockpacks...", blockpackDir);
        File[] files = blockpackDir.listFiles();
        List<BlockPack> packs = new ArrayList<BlockPack>();
        if (files == null)
        {
            return packs;
        }
        Map<String, BlockPack> found = new HashMap<String, BlockPack>();
        for (File file : files)
        {
            if (file.getName().startsWith("blockpack") || file.getName().startsWith("[converted]"))
            {
                BlockPack pack = null;
                if (!file.getName().endsWith(".zip") && file.isDirectory())
                {
                    pack = new FolderBlockPack(creativeBlock, file);
                }
                else if (file.getName().endsWith(".zip") && file.isFile())
                {
                    pack = new FileBlockPack(creativeBlock, file);
                }
                if (pack != null)
                {
                    if (found.containsKey(pack.getModId()))
                    {
                        BlockPack current = found.get(pack.getModId());
                        pack = pack.latest(current);

                        LogUtil.blockpack(BlockPack.class, "Found duplicate entries for {}, using latest version {}", pack.getModId(), pack.getVersion());
                    }
                    else
                    {
                        LogUtil.blockpack(BlockPack.class, "Found blockpack {}", pack);
                    }
                    found.put(pack.getModId(), pack);
                }
            }
        }
        packs.addAll(found.values());
        Collections.sort(packs);
        LogUtil.blockpack(BlockPack.class, "Found {} blockpacks", packs.size());
        return packs;
    }
}
