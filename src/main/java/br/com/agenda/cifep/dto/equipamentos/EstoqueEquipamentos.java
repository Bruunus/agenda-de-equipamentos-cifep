package br.com.agenda.cifep.dto.equipamentos;

public enum EstoqueEquipamentos {
	
	/* Os valores aqui representam os equipamentos oficiais em estoque */
	DATASHOW("Datashow"),
    NOTEBOOK("Notebook"),
    LASER_POINTER("Laser Pointer"),
    CABO_HDMI("Cabo HDMI"),
    EXTENSAO("Extensão"),
    ADAPTADOR("Adaptador"),
    FLIP_CHART("Flip Chart"),
    WEB_CAM("Webcam"),
    PEN_DRIVE("Pendrive"),
    CABO_P2("Cabo P2"),
    CABO_P10("Cabo P10"),
    MICROFONE("Microfone");

    private final String descricao;

    EstoqueEquipamentos(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

}
