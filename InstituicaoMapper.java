package Mapper;

import java.util.stream.Collectors;

import DTO.InstituicaoDTO;
import lp.Equipa6_Comp2.entity.Instituicao;

public class InstituicaoMapper {

    // ENTITY → DTO
    public static InstituicaoDTO toDTO(Instituicao inst) {

        return new InstituicaoDTO(
                inst.getId(),
                inst.getNome(),
                inst.getDataFundacao(),

                // lista de IDs
                inst.getProgramas()
                        .stream()
                        .map(p -> p.getId())
                        .collect(Collectors.toList()),

                // programa selecionado → só ID
                inst.getPrograma() != null ? inst.getPrograma().getId() : null
        );
    }

    // DTO → ENTITY
    public static Instituicao toEntity(InstituicaoDTO dto) {

        if(dto == null) return null;

        Instituicao inst = new Instituicao();

        if(dto.getId() != null) inst.setId(dto.getId());

        inst.setNome(dto.getNome());
        inst.setDataFundacao(dto.getDataFundacao());

        

        return inst;
    }
}
