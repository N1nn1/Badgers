package com.ninni.badgers.client.model;

import com.google.common.collect.ImmutableList;
import com.ninni.badgers.entity.BadgerEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.*;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.entity.model.AnimalModel;
import net.minecraft.util.math.MathHelper;

import static net.minecraft.client.render.entity.model.EntityModelPartNames.*;

@SuppressWarnings("FieldCanBeLocal, unused")
@Environment(EnvType.CLIENT)
public class BadgerEntityModel<T extends BadgerEntity> extends AnimalModel<T> {
    private final ModelPart root;

    private final ModelPart body;
    private final ModelPart tail;
    private final ModelPart leftArm;
    private final ModelPart leftLeg;
    private final ModelPart rightLeg;
    private final ModelPart rightArm;
    private final ModelPart head;
    private final ModelPart leftEar;
    private final ModelPart rightEar;

    public BadgerEntityModel(ModelPart root) {
        super(RenderLayer::getEntityCutoutNoCull, true, 10.0F, 1.5F, 2.0F, 2.0F, 24.0F);
        this.root = root;

        this.body = root.getChild(BODY);
        this.head = root.getChild(HEAD);

        this.rightEar = head.getChild(RIGHT_EAR);
        this.leftEar  = head.getChild(LEFT_EAR);
        
        this.rightArm = body.getChild(RIGHT_LEG);
        this.rightLeg = body.getChild(LEFT_LEG);
        this.leftArm  = body.getChild(RIGHT_ARM);
        this.leftLeg  = body.getChild(LEFT_ARM);
        this.tail     = body.getChild(TAIL);
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData data = new ModelData();
        ModelPartData root = data.getRoot();

        ModelPartData body = root.addChild(
            BODY,
            ModelPartBuilder.create()
                            .uv(0, 0)
                            .cuboid(-4.0F, -2.5F, -4.5F, 8.0F, 5.0F, 10.0F),
            ModelTransform.of(0.0F, 18.5F, 0.5F, 0.0F, 0.0F, 0.0F)
        );

        ModelPartData tail = body.addChild(
            TAIL,
            ModelPartBuilder.create()
                            .uv(0, 24)
                            .cuboid(-1.5F, -0.5F, 0.0F, 3.0F, 1.0F, 4.0F),
            ModelTransform.of(0.0F, -1.5F, 5.5F, -0.7854F, 0.0F, 0.0F)
        );

        ModelPartData leftArm = body.addChild(
            LEFT_ARM,
            ModelPartBuilder.create()
                            .uv(0, 0)
                            .cuboid(-1.0F, 0.0F, -1.0F, 2.0F, 3.0F, 3.0F),
            ModelTransform.of(2.0F, 2.5F, -3.0F, 0.0F, 0.0F, 0.0F)
        );

        ModelPartData leftLeg = body.addChild(
            LEFT_LEG,
            ModelPartBuilder.create()
                            .uv(0, 0)
                            .cuboid(-1.0F, 0.0F, -2.0F, 2.0F, 3.0F, 3.0F),
            ModelTransform.of(2.0F, 2.5F, 4.0F, 0.0F, 0.0F, 0.0F)
        );

        ModelPartData rightLeg = body.addChild(
            RIGHT_LEG,
            ModelPartBuilder.create()
                            .uv(0, 0)
                            .mirrored(true)
                            .cuboid(-1.0F, 0.0F, -2.0F, 2.0F, 3.0F, 3.0F),
            ModelTransform.of(-2.0F, 2.5F, 4.0F, 0.0F, 0.0F, 0.0F)
        );

        ModelPartData rightArm = body.addChild(
            RIGHT_ARM,
            ModelPartBuilder.create()
                            .uv(0, 0)
                            .mirrored(true)
                            .cuboid(-1.0F, 0.0F, -1.0F, 2.0F, 3.0F, 3.0F),
            ModelTransform.of(-2.0F, 2.5F, -3.0F, 0.0F, 0.0F, 0.0F)
        );

        ModelPartData head = root.addChild(
            HEAD,
            ModelPartBuilder.create()
                            .uv(0, 15)
                            .cuboid(-3.5F, -1.5F, -6.0F, 7.0F, 3.0F, 6.0F)
                            .uv(20, 15)
                            .cuboid(-1.5F, -1.5F, -8.0F, 3.0F, 3.0F, 2.0F),
            ModelTransform.of(0.0F, 15.5F, -3.0F, 0.3927F, 0.0F, 0.0F)
        );

        ModelPartData leftEar = head.addChild(
            LEFT_EAR,
            ModelPartBuilder.create()
                            .uv(0, 15)
                            .cuboid(-1.0F, -2.0F, -0.5F, 2.0F, 2.0F, 1.0F),
            ModelTransform.of(2.0F, -1.5F, -0.5F, 0.0F, 0.0F, 0.0F)
        );

        ModelPartData rightEar = head.addChild(
            RIGHT_EAR,
            ModelPartBuilder.create()
                            .uv(0, 15)
                            .mirrored(true)
                            .cuboid(-1.0F, -2.0F, -0.5F, 2.0F, 2.0F, 1.0F),
            ModelTransform.of(-2.0F, -1.5F, -0.5F, 0.0F, 0.0F, 0.0F)
        );

        return TexturedModelData.of(data, 48, 32);
    }

    @Override
    public void setAngles(T entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
        limbDistance = MathHelper.clamp(limbDistance, -0.45F, 0.45F);

        if (!entity.isAttacking()) {

            float speed = 6f;
            float degree = 3f;
            this.head.pitch = headPitch * 0.017453292F;
            this.head.yaw = headYaw * 0.017453292F;

            this.body.pitch = 0F;
            this.head.pitch += 0.3927F;
            this.head.pivotY = 15.5F;
            this.tail.pitch = -0.7854F;
            this.leftEar.pitch = 0F;
            this.rightEar.pitch = 0F;

            this.head.yaw += MathHelper.cos(2.0F + limbAngle * speed * 0.05F) * degree * 0.2F * limbDistance;
            this.leftArm.pitch = MathHelper.cos(limbAngle * speed * 0.2F) * degree * 1.2F * limbDistance;
            this.rightArm.pitch = MathHelper.cos(limbAngle * speed * 0.2F) * degree * -1.2F * limbDistance;
            this.leftLeg.pitch = MathHelper.cos(limbAngle * speed * 0.2F) * degree * -1.2F * limbDistance;
            this.rightLeg.pitch = MathHelper.cos(limbAngle * speed * 0.2F) * degree * 1.2F * limbDistance;
            this.leftArm.pivotY = MathHelper.cos(-1.0F + limbAngle * speed * 0.2F) * degree * -1F * limbDistance + 2.5F;
            this.rightArm.pivotY = MathHelper.cos(-1.0F + limbAngle * speed * 0.2F) * degree * 1F * limbDistance + 2.5F;
            this.leftLeg.pivotY = MathHelper.cos(-1.0F + limbAngle * speed * 0.2F) * degree * 1F * limbDistance + 2.5F;
            this.rightLeg.pivotY = MathHelper.cos(-1.0F + limbAngle * speed * 0.2F) * degree * -1F * limbDistance + 2.5F;
            this.tail.yaw = MathHelper.cos(1.0F + limbAngle * speed * 0.1F) * degree * 0.8F * limbDistance;
            this.body.roll = MathHelper.cos(limbAngle * speed * 0.1F) * degree * 0.2F * limbDistance;
            this.body.yaw = MathHelper.cos(1.0F + limbAngle * speed * 0.1F) * degree * 0.2F * limbDistance;
            this.leftArm.roll = MathHelper.cos(limbAngle * speed * 0.1F) * degree * -0.2F * limbDistance;
            this.rightLeg.roll = MathHelper.cos(limbAngle * speed * 0.1F) * degree * -0.2F * limbDistance;
            this.leftLeg.roll = MathHelper.cos(limbAngle * speed * 0.1F) * degree * -0.2F * limbDistance;
            this.rightArm.roll = MathHelper.cos(limbAngle * speed * 0.1F) * degree * -0.2F * limbDistance;
        }
        if (entity.isAttacking()) {
            float speed = 1.5f;
            float degree = 1f;
            this.leftArm.pitch = MathHelper.cos(limbAngle * speed * 0.4F) * degree * 2.8F * limbDistance;
            this.rightArm.pitch = MathHelper.cos(-1.0F + limbAngle * speed * 0.4F) * degree * 2.8F * limbDistance;
            this.leftLeg.pitch = MathHelper.cos(-1.0F + limbAngle * speed * 0.4F) * degree * 2.0F * limbDistance;
            this.rightLeg.pitch = MathHelper.cos(limbAngle * speed * 0.4F) * degree * 2.0F * limbDistance;
            this.body.pitch = MathHelper.cos(limbAngle * speed * 0.4F) * degree * 0.6F * limbDistance;
            this.head.pivotY = MathHelper.cos(limbAngle * speed * 0.4F) * degree * 3F * limbDistance + 16.5F;
            this.leftEar.pitch = MathHelper.cos(1.5F + limbAngle * speed * 0.4F) * degree * 1.0F * limbDistance;
            this.rightEar.pitch = MathHelper.cos(1.0F + limbAngle * speed * 0.4F) * degree * 1.0F * limbDistance;
            this.head.pitch = MathHelper.cos(1.0F + limbAngle * speed * 0.4F) * degree * 0.6F * limbDistance + 0.2F;
            this.tail.pitch = MathHelper.cos(limbAngle * speed * 0.4F) * degree * -0.6F * limbDistance - 0.2F;
            this.tail.yaw = MathHelper.cos(limbAngle * speed * 0.2F) * degree * 0.6F * limbDistance;
        }
    }

    @Override
    protected Iterable<ModelPart> getHeadParts() {
        return ImmutableList.of(this.head);
    }

    @Override
    protected Iterable<ModelPart> getBodyParts() {
        return ImmutableList.of(this.body);
    }
}
