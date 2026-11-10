package com.example.dealfinder_pc.vista.misComponentes

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.dealfinder_pc.R
import com.example.dealfinder_pc.ui.theme.DealFinder_PCTheme
import com.example.dealfinder_pc.viewmodel.uistate.PerfilUIState

@Composable
fun TarjetaPerfil(
    perfilData: PerfilUIState,
    onCuentaClick: () -> Unit,
    onPresupuestoClick: () -> Unit,
    onMonedaClick: () -> Unit,
    onTiendasClick: () -> Unit,
    onAvisosToggle: () -> Unit,
    onCerrarSesionClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(32.dp))

        TituloPantalla(textoRes = R.string.perfil_titulo)

        Spacer(modifier = Modifier.height(24.dp))

        TarjetaPerfil(
            nombre = perfilData.nombreUsuario,
            correo = perfilData.correoUsuario
        )

        Spacer(modifier = Modifier.height(8.dp))

        BotonOpciones(
            textoRes = R.string.perfil_correo_contrasena,
            mostrarFlecha = true,
            onClick = onCuentaClick
        )

        Spacer(modifier = Modifier.height(8.dp))

        BotonOpciones(
            textoRes = R.string.perfil_presupuesto_mensual,
            valorVerde = perfilData.presupuestoTexto,
            mostrarFlecha = false,
            onClick = onPresupuestoClick
        )

        Spacer(modifier = Modifier.height(8.dp))

        BotonOpciones(
            textoRes = R.string.perfil_moneda,
            valorVerde = perfilData.monedaActual,
            mostrarFlecha = true,
            onClick = onMonedaClick
        )

        Spacer(modifier = Modifier.height(8.dp))

        BotonOpciones(
            textoRes = R.string.perfil_tiendas_favoritas,
            valorGris = perfilData.tiendasTexto,
            mostrarFlecha = true,
            onClick = onTiendasClick
        )

        Spacer(modifier = Modifier.height(8.dp))

        BotonAvisos(
            textoRes = R.string.perfil_avisos_ofertas,
            activado = perfilData.avisosActivados,
            onClick = onAvisosToggle
        )

        Spacer(modifier = Modifier.height(8.dp))

        BotonPerfilSalir(
            textoRes = R.string.perfil_cerrar_sesion,
            onClick = onCerrarSesionClick
        )

        Spacer(modifier = Modifier.height(40.dp))
    }
}



@Preview(showBackground = true, backgroundColor = 0xFF191636)
@Composable
fun PanelPerfilPreview() {
    DealFinder_PCTheme {
        TarjetaPerfil(
            perfilData = PerfilUIState(
                nombreUsuario = "JUGADOR_01",
                correoUsuario = "jugador@correo.com",
                presupuestoTexto = "$80.00",
                monedaActual = "USD",
                tiendasTexto = "Steam, GOG",
                avisosActivados = true
            ),
            onCuentaClick = {},
            onPresupuestoClick = {},
            onMonedaClick = {},
            onTiendasClick = {},
            onAvisosToggle = {},
            onCerrarSesionClick = {}
        )
    }
}
