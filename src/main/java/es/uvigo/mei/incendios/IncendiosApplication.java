package es.uvigo.mei.incendios;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import es.uvigo.mei.incendios.daos.AreaDAO;
import es.uvigo.mei.incendios.daos.BrigadaDAO;
import es.uvigo.mei.incendios.daos.BrigadistaDAO;
import es.uvigo.mei.incendios.daos.ContratoDAO;
import es.uvigo.mei.incendios.daos.IncendioDAO;
import es.uvigo.mei.incendios.entidades.Area;
import es.uvigo.mei.incendios.entidades.Brigada;
import es.uvigo.mei.incendios.entidades.Brigadista;
import es.uvigo.mei.incendios.entidades.Contrato;
import es.uvigo.mei.incendios.entidades.EstadoIncendio;
import es.uvigo.mei.incendios.entidades.Incendio;
import es.uvigo.mei.incendios.entidades.Riesgo;

@SpringBootApplication
public class IncendiosApplication implements CommandLineRunner {
	@Autowired
	AreaDAO areaDAO;

	@Autowired
	BrigadaDAO brigadaDAO;

	@Autowired
	BrigadistaDAO brigadistaDAO;

	@Autowired
	ContratoDAO contratoDAO;

	@Autowired
	IncendioDAO incendioDAO;

	public static void main(String[] args) {
		SpringApplication.run(IncendiosApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		eliminarTodosLosDatos();
		crearEntidades();
		consultarEntidades();
	}

	// ter en conta si fai falta crear referencias a objetos que serán creados en crearEntidades()
	@Transactional
	private void crearEntidades() {
		Brigada b1 = new Brigada("Brigada de montes del concello de Ourense", "Base 1 polígono San Cibrao");
		Brigada b2 = new Brigada("Brigada antiincendios de Maceda", "Base Maceda");
		Brigada b3 = new Brigada("Brigada antiincendios de Pereiro de Aguiar", "Base Pereiro");
		b1 = brigadaDAO.save(b1);
		b2 = brigadaDAO.save(b2);
		b3 = brigadaDAO.save(b3);

		Brigadista brg1 = new Brigadista("12345678N", "José", "Núñez", "666111222");
		Brigadista brg2 = new Brigadista("444444444P", "Carlos", "Blanco", "678765432");
		Brigadista brg3 = new Brigadista("345431208C", "Sara", "Martínez", "722345678");
		brg1 = brigadistaDAO.save(brg1);
		brg2 = brigadistaDAO.save(brg2);
		brg3 = brigadistaDAO.save(brg3);

		Area a1 = new Area("Área Ourense ciudad", Riesgo.SIN_RIESGO, b1);
		Area a2 = new Area("Área da Limia", Riesgo.MEDIO, b2);
		Area a3 = new Area("Área de Maceda", Riesgo.ALTO, b3);
		Area a4 = new Area("Área de Trives", Riesgo.BAJO, b3);
		a1 = areaDAO.save(a1);
		a2 = areaDAO.save(a2);
		a3 = areaDAO.save(a3);
		a4 = areaDAO.save(a4);

		// créanse as listas de áreas e brigadas do incendio i1
		List<Brigada> brigadasIncendio1 = new ArrayList<>();
		brigadasIncendio1.add(b1);
		List<Area> areasIncendio1 = new ArrayList<>();
		areasIncendio1.add(a1);

		Incendio i1 = new Incendio(new Date(), "Incendio forestal que estuvo a punto de llegar a las casas. Se encuentra controlado", EstadoIncendio.CONTROLADO, brigadasIncendio1, areasIncendio1);
		i1 = incendioDAO.save(i1);

		// datos para o incendio 2
		List<Brigada> brigadasIncendio2 = new ArrayList<>();
		brigadasIncendio2.add(b2);
		brigadasIncendio2.add(b3);
		List<Area> areasIncendio2 = new ArrayList<>();
		areasIncendio2.add(a3);
		areasIncendio2.add(a2);

		// formato de fechas
		SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");

		String fechaStrIncendio2 = "2024-08-20";
		try {
			Date fechaIncendio2 = formatoFecha.parse(fechaStrIncendio2);
			Incendio i2 = new Incendio(fechaIncendio2, "Incendio forestal de grandes dimensiones que afecta a la comarca de la Limia y Maceda", EstadoIncendio.ACTIVO, brigadasIncendio2, areasIncendio2);
			i2 = incendioDAO.save(i2);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		String fechaStrContrato1 = "2024-07-01";
		try {
			Date fecha1 = formatoFecha.parse(fechaStrContrato1);
			Contrato c1 = new Contrato(fecha1, b3, brg3);
			c1 = contratoDAO.save(c1);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	@Transactional
	protected void consultarEntidades() {
		System.out.println("-------------------------------------------");
		List<Brigada> brigadas = brigadaDAO.findAll();
		System.out.println("Lista de brigadas:");
		for (Brigada b : brigadas) {
			System.out.println("\t" + b);
		}
		
		System.out.println("-------------------------------------------");

		System.out.println("-------------------------------------------");
		List<Area> areas = areaDAO.findAll();
		System.out.println("Lista de áreas:");
		for (Area a : areas) {
			System.out.println("\t" + a);
		}
		
		System.out.println("-------------------------------------------");

		System.out.println("-------------------------------------------");
		List<Brigadista> brigadistas = brigadistaDAO.findAll();
		System.out.println("Lista de brigadistas:");
		for (Brigadista brgd : brigadistas) {
			System.out.println("\t" + brgd);
		}
		
		// System.out.println("-------------------------------------------");
		// System.out.println("-------------------------------------------");
		// List<Incendio> incendios = incendioDAO.findAll();
		// System.out.println("Lista de incendios:");
		// for (Incendio i : incendios) {
		// 	System.out.println("\t" + i);
		// 	// Aquí se puede acceder a las brigadas, ya que estamos en el contexto transaccional
		// 	for (Brigada b : i.getBrigadasAtienden()) {
		// 		System.out.println("\t\tBrigadas que atienden: " + b);
		// 	}
		// 	// Lo mismo para las áreas
		// 	for (Area a : i.getAreasExtension()) {
		// 		System.out.println("\t\tÁreas en las que se extiende: " + a);
		// 	}
		// }
		
		// System.out.println("-------------------------------------------");

		System.out.println("-------------------------------------------");
		List<Contrato> contratos = contratoDAO.findAll();
		System.out.println("Lista de contratos:");
		for (Contrato c : contratos) {
			System.out.println("\t" + c);
		}
		
		System.out.println("-------------------------------------------");
	}

	// método de proba
	private void eliminarTodosLosDatos() {
		// Eliminar todos los incendios
		incendioDAO.deleteAll();
		System.out.println("Todos los incendios han sido eliminados.");

		// Eliminar todas las áreas
		areaDAO.deleteAll();
		System.out.println("Todas las áreas han sido eliminadas.");

		// Eliminar todos los contratos
		contratoDAO.deleteAll();
		System.out.println("Todos los contratos han sido eliminados.");

		// Eliminar todas las brigadas
		brigadaDAO.deleteAll();
		System.out.println("Todas las brigadas han sido eliminadas.");

		// Eliminar todos los brigadistas
		brigadistaDAO.deleteAll();
		System.out.println("Todos los brigadistas han sido eliminados.");

		// Mostrar el estado actual
		consultarEntidades();
	}
}
