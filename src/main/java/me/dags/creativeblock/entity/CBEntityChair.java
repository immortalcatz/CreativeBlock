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

package me.dags.creativeblock.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

/**
 * @author dags <dags@dags.me>
 */

public class CBEntityChair extends Entity
{
    private boolean hasMount = false;

    public CBEntityChair(World w, double x, double y, double z)
    {
        super(w);
        this.setPosition(x, y + this.getYOffset(), z);
        this.motionX = 0.0D;
        this.motionY = 0.0D;
        this.motionZ = 0.0D;
        this.prevPosX = x;
        this.prevPosY = y;
        this.prevPosZ = z;
        this.setSize(0.001F, 0.001F);
    }

    @Override
    public void onUpdate()
    {
        super.onUpdate();
        if (hasMount)
        {
            if (this.riddenByEntity == null)
            {
                removeEntity();
            }
        }
    }

    public void setMounted(boolean b)
    {
        hasMount = b;
    }

    public void removeEntity()
    {
        hasMount = false;
        this.worldObj.removeEntity(this);
    }

    @Override
    public double getMountedYOffset()
    {
        return (double) this.height * 0.0D - 0.333;
    }

    @Override
    protected void entityInit()
    {}

    @Override
    protected void readEntityFromNBT(NBTTagCompound tagCompund)
    {}

    @Override
    protected void writeEntityToNBT(NBTTagCompound tagCompound)
    {}

    public static boolean onInteractChair(World w, BlockPos pos, EntityPlayer ep, EnumFacing side, double mountOffset)
    {
        if (ep.isRiding() || side != EnumFacing.UP)
        {
            return false;
        }
        double xPos = pos.getX() + 0.5D;
        double yPos = pos.getY() + mountOffset;
        double zPos = pos.getZ() + 0.5D;
        CBEntityChair e = new CBEntityChair(w, xPos, yPos, zPos);
        e.setPosition(xPos, yPos, zPos);
        e.setMounted(true);
        w.spawnEntityInWorld(e);
        ep.mountEntity(e);
        if (e.riddenByEntity == null)
        {
            e.worldObj.removeEntity(e);
            return false;
        }
        return true;
    }
}
