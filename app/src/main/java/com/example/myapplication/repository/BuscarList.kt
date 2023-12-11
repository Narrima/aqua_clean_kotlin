package com.example.myapplication.repository
import com.example.myapplication.model.Estado
import com.example.myapplication.model.Praia


class BuscarList {
    companion object {
        val listaDeEstados: List<Estado> by lazy {
            // Inicialize a lista de praias de Santa Catarina aqui
            listOf(
                Estado("AMAPA"),
                Estado("MARANHÃO"),
                Estado("PIAUÍ"),
                Estado("CEARÁ"),
                Estado("RIO GRANDE DO NORTE"),
                Estado("PARAÍBA"),
                Estado("PERNAMBUCO"),
                Estado("ALAGOAS"),
                Estado("SERGIPE"),
                Estado("BAHIA"),
                Estado("ESPÍRITO SANTO"),
                Estado("RIO DE JANEIRO"),
                Estado("SÃO PAULO"),
                Estado("PARANÁ"),
                Estado("SANTA CATARINA"),
                Estado("RIO GRANDE DO SUL"),
                // Adicione mais itens conforme necessário
            )
        }
        val listaDePraiasAmapa: List<Praia> by lazy {
            // Inicialize a lista de praias de Santa Catarina aqui
            listOf(
                Praia("SaoPaulo"),
                Praia("SaoPaulo1"),
            )
        }

        val listaDePraiasMaranhao: List<Praia> by lazy {
            // Inicialize a lista de praias de Santa Catarina aqui
            listOf(
                Praia("SaoPaulo"),
                Praia("SaoPaulo1"),
            )
        }
        val listaDePraiasPiaui: List<Praia> by lazy {
            // Inicialize a lista de praias de Santa Catarina aqui
            listOf(
                Praia("SaoPaulo"),
                Praia("SaoPaulo1"),
            )
        }

        val listaDePraiasCeara: List<Praia> by lazy {
            // Inicialize a lista de praias de Santa Catarina aqui
            listOf(
                Praia("SaoPaulo"),
                Praia("SaoPaulo1"),
            )
        }
        val listaDePraiasRioGrandeDoNorte: List<Praia> by lazy {
            // Inicialize a lista de praias de Santa Catarina aqui
            listOf(
                Praia("SaoPaulo"),
                Praia("SaoPaulo1"),
            )
        }

        val listaDePraiasParnaiba: List<Praia> by lazy {
            // Inicialize a lista de praias de Santa Catarina aqui
            listOf(
                Praia("SaoPaulo"),
                Praia("SaoPaulo1"),
            )
        }
        val listaDePraiasPernambuco: List<Praia> by lazy {
            // Inicialize a lista de praias de Santa Catarina aqui
            listOf(
                Praia("SaoPaulo"),
                Praia("SaoPaulo1"),
            )
        }

        val listaDePraiasAlagoas: List<Praia> by lazy {
            // Inicialize a lista de praias de Santa Catarina aqui
            listOf(
                Praia("SaoPaulo"),
                Praia("SaoPaulo1"),
            )
        }
        val listaDePraiasSergipe: List<Praia> by lazy {
            // Inicialize a lista de praias de Santa Catarina aqui
            listOf(
                Praia("SaoPaulo"),
                Praia("SaoPaulo1"),
            )
        }

        val listaDePraiasBahia: List<Praia> by lazy {
            // Inicialize a lista de praias de Santa Catarina aqui
            listOf(
                Praia("SaoPaulo"),
                Praia("SaoPaulo1"),
            )
        }
        val listaDePraiasEspiritoSanto: List<Praia> by lazy {
            // Inicialize a lista de praias de Santa Catarina aqui
            listOf(
                Praia("SaoPaulo"),
                Praia("SaoPaulo1"),
            )
        }

        val listaDePraiasRioDeJaneiro: List<Praia> by lazy {
            // Inicialize a lista de praias de Santa Catarina aqui
            listOf(
                Praia("SaoPaulo"),
                Praia("SaoPaulo1"),
            )
        }
        val listaDePraiasSaoPaulo: List<Praia> by lazy {
            // Inicialize a lista de praias de Santa Catarina aqui
            listOf(
                Praia("SaoPaulo"),
                Praia("SaoPaulo1"),
            )
        }

        val listaDePraiasParana: List<Praia> by lazy {
            // Inicialize a lista de praias de Santa Catarina aqui
            listOf(
                Praia("SaoPaulo"),
                Praia("SaoPaulo1"),
            )
        }

        val listaDePraiasSantaCatarina: List<Praia> by lazy {
            // Inicialize a lista de praias de Santa Catarina aqui
            listOf(
                Praia("ARROIO DA PRAIA DAS GAIVOTAS"),
                Praia("CANAL DO LINGUADO"),
                Praia("LAGOA BRAVA"),
                Praia("LAGOA DA CONCEIÇÃO"),
                Praia("LAGOA DA FERRUGEM"),
                Praia("LAGOA DE BARRA VELHA"),
                Praia("LAGOA DO FAXINAL"),
                Praia("LAGOA DO PERI"),
                Praia("LAGOA DOS FREITAS"),
                Praia("PRAIA DA ARMAÇÃO DO PÂNTANO DO SUL"),
                Praia("PRAIA DA BARRA DA LAGOA"),
                Praia("PRAIA DA BARRA DO SUL"),
                Praia("PRAIA DA BARRA VELHA"),
                Praia("PRAIA DA BASE AÉREA"),
                Praia("PRAIA DA BEIRA MAR NORTE"),
                Praia("PRAIA DA CACHOEIRA DO BOM JESUS"),
                Praia("PRAIA DA CAIACANGAÇÚ"),
                Praia("PRAIA DA CAIEIRA"),
                Praia("PRAIA DA DANIELA"),
                Praia("PRAIA DA JOAQUINA"),
                Praia("PRAIA DA LAGOINHA"),
                Praia("PRAIA DA SAUDADE"),
                Praia("PRAIA DA SOLIDÃO"),
                Praia("PRAIA DA TAPERA"),
                Praia("PRAIA DA VIGIA"),
                Praia("BALNEÁRIO GAIVOTAS"),
                Praia("PRAIA DAS PALMEIRAS"),
                Praia("PRAIA DE BOMBAS"),
                Praia("PRAIA DE BOMBINHAS"),
                Praia("PRAIA DE CANASVIEIRAS"),
                Praia("PRAIA DE GAROPABA"),
                Praia("PRAIA DE JERERE"),
                Praia("PRAIA DE JURERE INTERNACIONA"),
                Praia("PRAIA DE LARANJEIRAS"),
                Praia("PRAIA DE MOÇAMBIQUE"),
                Praia("PRAIA DE PIÇARRAS"),
                Praia("PRAIA DE PONTA DAS CANAS"),
                Praia("PRAIA DE SAMBAQUI"),
                Praia("PRAIA DE SANTO ANTÔNIO DE LISBOA"),
                Praia("PRAIA DE SÃO MIGUEL"),
                Praia("PRAIA DE TAQUARAS"),
                Praia("PRAIA DE ZIMBROS"),
                Praia("PRAIA DO ARROIO DO SILVA"),
                Praia("PRAIA DO BALNEÁRIO"),
                Praia("PRAIA DO BALNEÁRIO CAMBORIÚ"),
                Praia("PRAIA DO BOM ABRIGO"),
                Praia("PRAIA DO CACUPÉ"),
                Praia("PRAIA DO CANTO GRANDE"),
                Praia("PRAIA DO CERRO"),
                Praia("PRAIA DO ESTALEIRINHO"),
                Praia("PRAIA DO ESTALEIRO"),
                Praia("PRAIA DO FORTE"),
                Praia("PRAIA DO ITAGUAÇU"),
                Praia("PRAIA DO JARDIM ATLÂNTICO"),
                Praia("PRAIA DO JOSÉ MENDES"),
                Praia("PRAIA DO MARISCAL"),
                Praia("PRAIA DO MATADOURO"),
                Praia("PRAIA DO MEIO"),
                Praia("PRAIA DO MOCAMBIQUE"),
                Praia("PRAIA DO MORRO DAS PEDRAS"),
                Praia("PRAIA DO MORRO DOS CONVENTOS"),
                Praia("PRAIA DO PANTANO DO SUL"),
                Praia("PRAIA DO RIBEIRÃO DA ILHA"),
                Praia("PRAIA DO RINCÃO"),
                Praia("PRAIA DO SANTINHO"),
                Praia("PRAIA DO SIRIÚ"),
                Praia("PRAIA DOS INGLESES"),
                Praia("PRAIA MOLE"),
                // Adicione mais itens conforme necessário
            )
        }
        val listaDePraiasRioGrandeDoSul: List<Praia> by lazy {
            // Inicialize a lista de praias de Santa Catarina aqui
            listOf(
                Praia("SaoPaulo"),
                Praia("SaoPaulo1"),
            )
        }
    }
}