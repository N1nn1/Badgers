package com.ninni.badgers.client.model.entity;

import com.google.common.collect.ImmutableList;
import com.ninni.badgers.entity.BadgerEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.*;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.entity.model.AnimalModel;
import net.minecraft.util.math.MathHelper;

@SuppressWarnings("FieldCanBeLocal, unused")
@Environment(EnvType.CLIENT)
public class BadgerEntityModel<T extends BadgerEntity> extends AnimalModel<T> {
    private final ModelPart root;

    private final ModelPart body;
    private final ModelPart head;
    private final ModelPart leftEar;
    private final ModelPart rightEar;
    private final ModelPart leftWhiskers;
    private final ModelPart rightWhiskers;
    private final ModelPart tail;
    private final ModelPart leftArm;
    private final ModelPart rightArm;
    private final ModelPart rightLeg;
    private final ModelPart leftLeg;

    public BadgerEntityModel(ModelPart root) {
        super(RenderLayer::getEntityCutoutNoCull, true, 10.0F, 1.5F, 2.0F, 2.0F, 24.0F);
        this.root = root;

        this.body = root.getChild("body");
        this.head = root.getChild("head");

        this.leftLeg = body.getChild("left_leg");
        this.rightLeg = body.getChild("right_leg");
        this.leftArm = body.getChild("left_arm");
        this.rightArm = body.getChild("right_arm");
        this.tail = body.getChild("tail");

        this.leftWhiskers = head.getChild("left_whiskers");
        this.rightWhiskers = head.getChild("right_whiskers");
        this.leftEar = head.getChild("left_ear");
        this.rightEar = head.getChild("right_ear");
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData data = new ModelData();
        ModelPartData root = data.getRoot();

        ModelPartData body = root.addChild(
            "body",
            ModelPartBuilder.create()
                            .uv(0, 0)
                            .cuboid(-3.0F, -2.5F, -5.0F, 6.0F, 5.0F, 11.0F),
            ModelTransform.pivot(0.0F, 18.5F, 1.0F)
        );

        ModelPartData head = root.addChild(
            "head",
            ModelPartBuilder.create()
                            .uv(0, 16)
                            .cuboid(-2.5F, -3.0F, -3.0F, 5.0F, 3.0F, 3.0F)
                            .uv(16, 16)
                            .cuboid(-2.0F, -2.0F, -6.0F, 4.0F, 2.0F, 3.0F),
            ModelTransform.pivot(0.0F, 18.5F, -3.5F)
        );

        ModelPartData left_ear = head.addChild(
            "left_ear",
            ModelPartBuilder.create()
                            .uv(0, 8)
                            .cuboid(0.0F, -2.0F, 0.1F, 2.0F, 2.0F, 0.0F),
            ModelTransform.of(1.0F, -2.0F, -0.6F, 0.0F, -0.3927F, 0.0F)
        );

        ModelPartData right_ear = head.addChild(
            "right_ear",
            ModelPartBuilder.create()
                            .uv(0, 8)
                            .mirrored(true)
                            .cuboid(-2.0F, -2.0F, 0.0F, 2.0F, 2.0F, 0.0F),
            ModelTransform.of(-1.0F, -2.0F, -0.5F, 0.0F, 0.3927F, 0.0F)
        );

        ModelPartData left_whiskers = head.addChild(
            "left_whiskers",
            ModelPartBuilder.create()
                            .uv(7, 0)
                            .cuboid(0.0F, -1.0F, 0.0F, 2.0F, 2.0F, 0.0F),
            ModelTransform.of(2.5F, -1.0F, -1.0F, 0.0F, -0.6981F, 0.0F)
        );

        ModelPartData right_whiskers = head.addChild(
            "right_whiskers",
            ModelPartBuilder.create()
                            .uv(7, 0)
                            .mirrored(true)
                            .cuboid(-2.0F, -1.0F, 0.0F, 2.0F, 2.0F, 0.0F),
            ModelTransform.of(-2.5F, -1.0F, -1.0F, 0.0F, 0.6981F, 0.0F)
        );

        ModelPartData tail = body.addChild(
            "tail",
            ModelPartBuilder.create()
                            .uv(23, 6)
                            .cuboid(-1.0F, 0.0F, -1.0F, 2.0F, 3.0F, 2.0F),
            ModelTransform.of(0.0F, -2.0F, 6.0F, 0.3927F, 0.0F, 0.0F)
        );

        ModelPartData left_arm = body.addChild(
            "left_arm",
            ModelPartBuilder.create()
                            .uv(23, 0)
                            .cuboid(-1.0F, -1.0F, -1.0F, 2.0F, 4.0F, 2.0F),
            ModelTransform.pivot(2.0F, 2.5F, -3.5F)
        );

        ModelPartData right_arm = body.addChild(
            "right_arm",
            ModelPartBuilder.create()
                            .uv(23, 0)
                            .mirrored(true)
                            .cuboid(-0.5F, -1.0F, -1.0F, 2.0F, 4.0F, 2.0F),
            ModelTransform.pivot(-2.5F, 2.5F, -3.5F)
        );

        ModelPartData right_leg = body.addChild(
            "right_leg",
            ModelPartBuilder.create()
                            .uv(0, 0)
                            .mirrored(true)
                            .cuboid(-1.0F, -2.0F, -1.5F, 2.0F, 5.0F, 3.0F),
            ModelTransform.pivot(-2.5F, 2.5F, 3.5F)
        );

        ModelPartData left_leg = body.addChild(
            "left_leg",
            ModelPartBuilder.create()
                            .uv(0, 0)
                            .cuboid(-1.0F, -2.0F, -1.5F, 2.0F, 5.0F, 3.0F),
            ModelTransform.pivot(2.5F, 2.5F, 3.5F)
        );

        return TexturedModelData.of(data, 48, 32);
    }

    @Override
    public void setAngles(T entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
        //if (entity.isSleeping()) {
            //float speed = 1.0f;
            //float degree = 1.0f;
            //this.body.roll = MathHelper.cos(limbAngle * speed * 0.025F) * degree * 0.01F * limbDistance - 1.57F;
            //this.body.pivotY = 0.17F + 20.5F;
            //this.body.pitch = MathHelper.cos(limbDistance * speed * 0.05F) * degree * 0.1F * limbDistance - 0.4F;
            //this.head.roll = MathHelper.cos(1.0F + limbDistance * speed * 0.025F) * degree * 0.3F * limbDistance + 0.6F;
            //this.head.pitch = 0.2F;
            //this.left_arm.pitch = MathHelper.cos(limbAngle * speed * 0.1F) * degree * 0.2F * limbDistance + 0.8F;
            //this.right_arm.pitch = MathHelper.cos(limbAngle * speed * 0.1F) * degree * 0.2F * limbDistance + 1.0F;
            //this.left_leg.pitch = MathHelper.cos(limbAngle * speed * 0.1F) * degree * 0.2F * limbDistance - 0.8F;
            //this.right_leg.pitch = MathHelper.cos(limbAngle * speed * 0.1F) * degree * 0.2F * limbDistance - 0.9F;
            //this.left_leg.yaw = MathHelper.cos(limbAngle * speed * 0.1F) * degree * 0.2F * limbDistance + 0.4F;
            //this.left_arm.yaw = MathHelper.cos(limbAngle * speed * 0.1F) * degree * 0.2F * limbDistance - 0.8F;
            //this.tail.roll = MathHelper.cos(limbAngle * speed * 0.05F) * degree * 0.4F * limbDistance + 0.8F;
        //} else {
            float speed = 6f;
            float degree = 3f;
            this.body.pivotY = 18.5F;
            this.body.pitch = 0F;
            this.leftArm.yaw = 0F;
            this.head.roll = 0F;
            this.leftLeg.yaw = 0F;
            this.tail.roll = 0F;

            this.head.pitch = headPitch * 0.017453292F;
            this.head.yaw = headYaw * 0.017453292F;
            this.leftArm.pitch = MathHelper.cos(limbAngle * speed * 0.2F) * degree * 1.2F * limbDistance;
            this.rightArm.pitch = MathHelper.cos(limbAngle * speed * 0.2F) * degree * -1.2F * limbDistance;
            this.leftLeg.pitch = MathHelper.cos(limbAngle * speed * 0.2F) * degree * -1.2F * limbDistance;
            this.rightLeg.pitch = MathHelper.cos(limbAngle * speed * 0.2F) * degree * 1.2F * limbDistance;
            this.leftArm.pivotY = MathHelper.cos(-1.0F + limbAngle * speed * 0.2F) * degree * -0.5F * limbDistance + 2.5F;
            this.rightArm.pivotY = MathHelper.cos(-1.0F + limbAngle * speed * 0.2F) * degree * 0.5F * limbDistance + 2.5F;
            this.leftLeg.pivotY = MathHelper.cos(-1.0F + limbAngle * speed * 0.2F) * degree * 0.5F * limbDistance + 2.5F;
            this.rightLeg.pivotY = MathHelper.cos(-1.0F + limbAngle * speed * 0.2F) * degree * -0.5F * limbDistance + 2.5F;
            this.tail.yaw = MathHelper.cos(1.0F + limbAngle * speed * 0.1F) * degree * 1.6F * limbDistance;
            this.body.roll = MathHelper.cos(limbAngle * speed * 0.1F) * degree * 0.2F * limbDistance;
            this.body.yaw = MathHelper.cos(1.0F + limbAngle * speed * 0.1F) * degree * 0.2F * limbDistance;
            this.leftArm.roll = MathHelper.cos(limbAngle * speed * 0.1F) * degree * -0.2F * limbDistance;
            this.rightLeg.roll = MathHelper.cos(limbAngle * speed * 0.1F) * degree * -0.2F * limbDistance;
            this.leftLeg.roll = MathHelper.cos(limbAngle * speed * 0.1F) * degree * -0.2F * limbDistance;
            this.rightArm.roll = MathHelper.cos(limbAngle * speed * 0.1F) * degree * -0.2F * limbDistance;
        //}

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
