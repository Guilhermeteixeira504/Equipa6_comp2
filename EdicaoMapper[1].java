package Mapper;

import java.util.List;

import DTO.EdicaoDTO;
import lp.Equipa6_Comp2.entity.Edicao;
import lp.Equipa6_Comp2.entity.Inscricao;
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
                edicao.getInscricoes(),
                edicao.getPrograma()
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
        edicao.setInscricoes(dto.getInscricoes());
        edicao.setPrograma(dto.getPrograma());

        return edicao;
    }


    // helper opcional: alterar programa
    public static void setPrograma(Edicao edicao, ProgramaVoluntariado programa) {
        edicao.setPrograma(programa);
    }

    // helper opcional: alterar lista de inscrições
    public static void setInscricoes(Edicao edicao, List<Inscricao> inscricoes) {
        edicao.setInscricoes(inscricoes);
    }
}
