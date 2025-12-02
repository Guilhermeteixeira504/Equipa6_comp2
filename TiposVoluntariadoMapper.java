package Mapper;

import java.util.List;

import DTO.TiposVoluntariadoDTO;
import lp.Equipa6_Comp2.entity.TiposVoluntariado;
import lp.Equipa6_Comp2.entity.ProgramaVoluntariado;

public class TiposVoluntariadoMapper {

    // ENTITY → DTO
    public static TiposVoluntariadoDTO toDTO(TiposVoluntariado tipo) {

        return new TiposVoluntariadoDTO(
                tipo.getId(),
                tipo.getNome(),
                tipo.getProgramas(),   // lista programs
                tipo.getPrograma()     // programa único
        );
    }

    // DTO → ENTITY
    public static TiposVoluntariado toEntity(TiposVoluntariadoDTO dto) {

        if (dto == null) return null;

        TiposVoluntariado tipo = new TiposVoluntariado();

        if (dto.getId() != null) {
            tipo.setId(dto.getId());
        }

        tipo.setNome(dto.getNome());
        tipo.setProgramas(dto.getProgramas());
        tipo.setPrograma(dto.getPrograma());

        return tipo;
    }

    
    public static void setPrograma(TiposVoluntariado tipo, ProgramaVoluntariado programa) {
        tipo.setPrograma(programa);
    }

    
    public static void setProgramas(TiposVoluntariado tipo, List<ProgramaVoluntariado> programas) {
        tipo.setProgramas(programas);
    }
}
