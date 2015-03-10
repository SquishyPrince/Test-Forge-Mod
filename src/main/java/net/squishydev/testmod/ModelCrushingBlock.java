package net.squishydev.testmod;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelCrushingBlock extends ModelBase
{
  //fields
    ModelRenderer Base;
    ModelRenderer S1;
    ModelRenderer S2;
    ModelRenderer S3;
    ModelRenderer S4;
  
  public ModelCrushingBlock()
  {
    textureWidth = 32;
    textureHeight = 32;
    
      Base = new ModelRenderer(this, 0, 0);
      Base.addBox(0F, 0F, 0F, 16, 12, 16);
      Base.setRotationPoint(-8F, 12F, -8F);
      Base.setTextureSize(32, 32);
      Base.mirror = true;
      setRotation(Base, 0F, 0F, 0F);
      S1 = new ModelRenderer(this, 0, 0);
      S1.addBox(0F, 0F, 0F, 16, 2, 2);
      S1.setRotationPoint(-8F, 10F, -8F);
      S1.setTextureSize(32, 32);
      S1.mirror = true;
      setRotation(S1, 0F, 0F, 0F);
      S2 = new ModelRenderer(this, 0, 0);
      S2.addBox(0F, 0F, 0F, 16, 2, 2);
      S2.setRotationPoint(-8F, 10F, 6F);
      S2.setTextureSize(32, 32);
      S2.mirror = true;
      setRotation(S2, 0F, 0F, 0F);
      S3 = new ModelRenderer(this, 0, 0);
      S3.addBox(0F, 0F, 0F, 2, 2, 12);
      S3.setRotationPoint(6F, 10F, -6F);
      S3.setTextureSize(32, 32);
      S3.mirror = true;
      setRotation(S3, 0F, 0F, 0F);
      S4 = new ModelRenderer(this, 0, 0);
      S4.addBox(0F, 0F, 0F, 2, 2, 12);
      S4.setRotationPoint(-8F, 10F, -6F);
      S4.setTextureSize(32, 32);
      S4.mirror = true;
      setRotation(S4, 0F, 0F, 0F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5);
    Base.render(f5);
    S1.render(f5);
    S2.render(f5);
    S3.render(f5);
    S4.render(f5);
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z)
  {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
  
  public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.setRotationAngles(f, f1, f2, f3, f4, f5, null);
  }

}