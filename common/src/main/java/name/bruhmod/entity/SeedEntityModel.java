package name.bruhmod.entity;

import name.bruhmod.LeMod;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;

public class SeedEntityModel extends HierarchicalModel<SeedEntity> {

    public static final ModelLayerLocation LOCATION = new ModelLayerLocation(LeMod.idOf("seed"), "main");

    private final ModelPart root;

    public SeedEntityModel(ModelPart root) {
        this.root = root.getChild(LOCATION.getLayer());
    }

    public static LayerDefinition createLayer() {
        MeshDefinition meshDef = new MeshDefinition();
        PartDefinition partDef = meshDef.getRoot();
        partDef.addOrReplaceChild(LOCATION.getLayer(), CubeListBuilder.create().texOffs(0, 0).addBox(-.5f, -.5f, -.5f, 1f, 1f, 1f), PartPose.ZERO);
        return LayerDefinition.create(meshDef, 4, 3);
    }

    @Override
    public ModelPart root() {
        return this.root;
    }

    @Override
    public void setupAnim(SeedEntity seedEntity, float v, float v1, float v2, float v3, float v4) {

    }
}
