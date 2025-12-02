package Mapper;

import java.util.stream.Collectors;

import DTO.EdicaoDTO;
import lp.Equipa6_Comp2.entity.Edicao;
import lp.Equipa6_Comp2.entity.ProgramaVoluntariado;

public class EdicaoMapper {

    // ENTITY → DTO
    public static EdicaoDTO toDTO(Edicao edicao) {

        return new EdicaoDTO(
                edicao.getId(),
                edicao.getAno(),
                edicao.getNumeroVagas(),
                edicao.getDataInicio(),
                edicao.getDataFim(),
                edicao.getInscricoes().stream().map(insc -> insc.getId()).collect(Collectors.toList()),
                edicao.getPrograma().getId()
        );
    }


    // DTO → ENTITY
    public static Edicao toEntity(EdicaoDTO dto) {

        if (dto == null) return null;

        Edicao edicao = new Edicao();

        // id
        edicao.setId(dto.getId());

        edicao.setAno(dto.getAno());
        edicao.setNumeroVagas(dto.getNumeroVagas());
        edicao.setDataInicio(dto.getDataInicio());
        edicao.setDataFim(dto.getDataFim());
        
        ProgramaVoluntariado p = new ProgramaVoluntariado();
	    p.setId(dto.getProgramaId());
	    edicao.setPrograma(p);

        return edicao;
    }

}
