package net.squishydev.testmod;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.World;

public class BuildingHandler {
	
	Block[][][] mapVar1; //controller Right face from top down North up
	Block[][][] mapVar2; //controller Up
	Block[][][] mapVar3; //controller Left
	Block[][][] mapVar4; //controller Down
	
	List<Block> validBlocks;
	
	public BuildingHandler(Block[][][] map, List<Block> validBlocks) {
		if (map!=null){
			this.mapVar1=map;
			this.mapVar2=new Block[map.length][map[0][0].length][map[0].length];
			this.mapVar3=new Block[map.length][map[0].length][map[0][0].length];
			this.mapVar4=new Block[map.length][map[0][0].length][map[0].length];
			findVariants();
		}
		if (validBlocks!=null) {
			this.validBlocks=validBlocks;
		}
	}
	
	private void findVariants() {
		for (int i = 0;i<this.mapVar4.length;i++) {
			for (int j = 0; j<this.mapVar4[0].length;j++) {
				for (int k = 0;k<this.mapVar4[0][0].length;k++) {
					this.mapVar4[i][j][k] = this.mapVar1[i][k][j];
				}
			}
		}
		
		for (int i = 0;i<this.mapVar3.length;i++) {
			for (int j = 0; j<this.mapVar3[0].length;j++) {
				for (int k = 0;k<this.mapVar3[0][0].length;k++) {
					this.mapVar3[i][j][k] = this.mapVar1[i][j][this.mapVar3[i][j].length-k-1];
					this.mapVar3[i][j][this.mapVar3[i][j].length-k-1]=this.mapVar1[i][j][k];
				}
			}
		}
		
		for (int i = 0;i<this.mapVar2.length;i++) {
			for (int j = 0; j<this.mapVar2[0].length;j++) {
				for (int k = 0;k<this.mapVar2[0][0].length;k++) {
					this.mapVar2[i][j][k] = this.mapVar1[i][k][j];
				}
			}
		}
		for (int j = 0;j<mapVar2.length;j++){ 
			int rows = mapVar2[j].length;
			Block[] temp;
			for(int i = 0; i < rows/2; i++){
				temp = mapVar2[j][rows -  i - 1];
				mapVar2[j][rows -  i - 1] = mapVar2[j][i];
				mapVar2[j][i] = temp;
			}
		}
	}
	
	public boolean  checkBuilding(AssemblyResult result, int Sx, int Sy, int Sz, World worldObj, int maxBlocks, Block parent) {
		Block[][][] temp = null;
		try {
			temp = assemble(result, Sx, Sy, Sz, worldObj, maxBlocks, parent);
			for (int i = 0;i<temp.length;i++) {
				System.out.println();
				for (int j = 0;j<temp[0].length;j++) {
					System.out.println();
					for (int k = 0;k<temp[0][0].length;k++) {
						System.out.print(temp[i][j][k].getUnlocalizedName().substring(temp[i][j][k].getUnlocalizedName().length()));
					}
				}
			}
		} catch (OverFlowException e) {
			result.resultCode = AssemblyResult.OVERFLOW;
		} catch (Error e) {
			result.resultCode = AssemblyResult.ERROR;
		}
		if (temp!=null) {
			System.out.println("Good!");
			return true;
		} else {
			return false;
		}
	}

	private Block[][][] assemble(AssemblyResult result, int sx, int sy, int sz, World worldObj, int maxBlocks, Block parent) {
		HashSet<ChunkPosition> openset = new HashSet<ChunkPosition>();
		HashSet<ChunkPosition> closedset = new HashSet<ChunkPosition>();
		List<ChunkPosition> iterator = new ArrayList<ChunkPosition>();
		
		Block[][][] temp = new Block[mapVar1.length][mapVar1[0].length][mapVar1[0][0].length];
		
		int x = sx, y = sy, z = sz;
		
		openset.add(new ChunkPosition(sx, sy, sz));
		while (!openset.isEmpty())
		{
			iterator.addAll(openset);
			for (ChunkPosition pos : iterator)
			{
				openset.remove(pos);
				if (closedset.contains(pos))
				{
					continue;
				}
				if (result.assembledBlocks.size() > maxBlocks)
				{
					throw new OverFlowException();
				}
				
				x = pos.chunkPosX;
				y = pos.chunkPosY;
				z = pos.chunkPosZ;
				
				closedset.add(pos);
				
				Block block = worldObj.getBlock(x, y, z);
				
				if (!canUseBlockForStructure(block, x, y, z))
				{
					continue;
				}
				
				temp[y-sy][z-sz][x-sx] = block;
				
				FoundBlock lb = new FoundBlock(block, worldObj.getBlockMetadata(x, y, z), worldObj.getTileEntity(x, y, z), pos);
				result.assembleBlock(lb);
				if (block == parent && result.controllerBlock == null)
				{
					result.controllerBlock = lb;
				}
				
				openset.add(new ChunkPosition(x - 1, y, z));
				openset.add(new ChunkPosition(x, y - 1, z));
				openset.add(new ChunkPosition(x, y, z - 1));
				openset.add(new ChunkPosition(x + 1, y, z));
				openset.add(new ChunkPosition(x, y + 1, z));
				openset.add(new ChunkPosition(x, y, z + 1));
			}
		}
		for (int i = 0;i<temp.length;i++) {
			for (int j = 0;j<temp[0].length;j++) {
				for (int k = 0;k<temp[0][0].length;k++) {
					if (temp[i][j][k]==null) {
						temp[i][j][k]=Blocks.air;
					}
				}
			}
		}
		return temp;
	}

	private boolean canUseBlockForStructure(Block block, int x, int y, int z) {
		if (this.validBlocks.contains(block)) {
			return true;
		}
		return false;
	}
}
