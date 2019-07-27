package utils

import grails.transaction.Transactional

import java.text.Normalizer
import java.text.SimpleDateFormat
import org.codehaus.groovy.grails.commons.GrailsApplication

@Transactional
class FechaService {

	GrailsApplication grailsApplication

	static WEEK_DAYS = [1: 'Domingo', 2: 'Lunes', 3: 'Martes', 4: 'Miercoles', 5: 'Jueves', 6: 'Viernes', 7: 'Sabado']

	static DOMINGO = 1
	static LUNES = 2
	static MARTES = 3
	static MIERCOLES = 4
	static JUEVES = 5
	static VIERNES = 6
	static SABADO = 7
	static MONTH_LIST = ['ENERO', 'FEBRERO', 'MARZO', 'ABRIL', 'MAYO', 'JUNIO', 'JULIO', 'AGOSTO', 'SEPTIEMBRE', 'OCTUBRE', 'NOVIEMBRE', 'DICIEMBRE']
	static ENERO = 1
	static FEBRERO = 2
	static MARZO = 3
	static ABRIL = 4
	static MAYO = 5
	static JUNIO = 6
	static JULIO = 7
	static AGOSTO = 8
	static SEPTIEMBRE = 9
	static OCTUBRE = 10
	static NOVIEMBRE = 11
	static DICIEMBRE = 12

	int getCurrentDayOfWeek(){
		Calendar calendar = Calendar.getInstance()
		return calendar.get(Calendar.DAY_OF_WEEK);
	}

	int getDayOfWeek(Date fecha){
		Calendar calendar = Calendar.getInstance()
		calendar.setTime(fecha)
		return calendar.get(Calendar.DAY_OF_WEEK);
	}

	int getDia(Date fecha){
		Calendar startCalendar = new GregorianCalendar();
		startCalendar.setTime(fecha);
		int diffMonth = startCalendar.get(Calendar.DATE);

		return diffMonth
	}

	int getMes(Date fecha){
		Calendar startCalendar = new GregorianCalendar();
		startCalendar.setTime(fecha);
		int diffMonth = 1 + startCalendar.get(Calendar.MONTH)

		return diffMonth
	}

	boolean mismoMesyAnio(Date fecha1,Date fecha2){
		Calendar startCalendar = new GregorianCalendar();
		startCalendar.setTime(fecha1);
		Calendar endCalendar = new GregorianCalendar();
		startCalendar.setTime(fecha2);

		int meses = startCalendar.get(Calendar.MONTH) - endCalendar.get(Calendar.MONTH)
		int aos = startCalendar.get(Calendar.YEAR) - endCalendar.get(Calendar.YEAR)

		if((meses == 0)&&(aos == 0)){
			return true
		}
		else{
			return false
		}
	}

	Date formatearFechaPrimerDiaMes(Date fecha){
		Calendar startCalendar = new GregorianCalendar();
		startCalendar.setTime(fecha);
		startCalendar.add(Calendar.DATE,-startCalendar.get(Calendar.DATE)+1)
		startCalendar.add(Calendar.HOUR,-startCalendar.get(Calendar.HOUR))
		startCalendar.add(Calendar.SECOND,-startCalendar.get(Calendar.SECOND	))
		startCalendar.add(Calendar.MINUTE,-startCalendar.get(Calendar.MINUTE))

		Date diffMonth = startCalendar.getTime();

		return diffMonth
	}

	Date formatearFechaPrimerDiaMes(String mes, String ano){

		return Date.parse("dd/MM/yyyy", "01/"+mes+"/"+ano)
	}

	int getAnio(Date fecha){
		Calendar startCalendar = new GregorianCalendar();
		startCalendar.setTime(fecha);
		int diffMonth = startCalendar.get(Calendar.YEAR);

		return diffMonth
	}

	Date parsear(String fecha){
		String form=grailsApplication.config.JUKU.default.formatoFecha
		Date f=new Date();
		if(form!=null && form != "[:]"){
			SimpleDateFormat sdf=new SimpleDateFormat(form);
			f=sdf.parse(fecha);
		}
		else{
			SimpleDateFormat sdf=new SimpleDateFormat('dd/MM/yyyy');
			f=sdf.parse(fecha);
		}

		return f;
	}

	Date ultimoDiaDelMes(Date fecha){
		Calendar startCalendar = Calendar.getInstance()
		startCalendar.setTime(fecha)
		startCalendar.set(Calendar.DATE, startCalendar.getActualMaximum(Calendar.DATE))

		Date ultimoDia = startCalendar.getTime()

		return ultimoDia
	}

	Date ultimoDiaDelMes(String mes, String ano){
		Date fecha = formatearFechaPrimerDiaMes(mes, ano)
		fecha = ultimoDiaDelMes(fecha)

		return fecha
	}

	String nombreMesES(Date fecha){
		SimpleDateFormat sdf = new SimpleDateFormat("MMMM", new Locale("es", "ES"))

		return sdf.format(fecha)
	}

	int mesEnStringAInt(String mes){
		int result

		switch(mes.toLowerCase()){
			case 'enero':
				result = ENERO
				break
			case 'febrero':
				result = FEBRERO
				break
			case 'marzo':
				result = MARZO
				break
			case 'abril':
				result = ABRIL
				break
			case 'mayo':
				result = MAYO
				break
			case 'junio':
				result = JUNIO
				break
			case 'julio':
				result = JULIO
				break
			case 'agosto':
				result = AGOSTO
				break
			case 'septiembre':
				result = SEPTIEMBRE
				break
			case 'octubre':
				result = OCTUBRE
				break
			case 'noviembre':
				result = NOVIEMBRE
				break
			case 'diciembre':
				result = DICIEMBRE
				break
			default:
				result = 1
				break
		}

		return result
	}

	def periodoEnStringAFecha(String mesDesde, String mesHasta, int anoDesde){
		int desde = mesEnStringAInt(mesDesde)
		int hasta = mesEnStringAInt(mesHasta)

		Date fechaDesde
		Date fechaHasta

		fechaDesde = Date.parse("dd/MM/yyyy", "01/"+desde+"/"+anoDesde)
		fechaHasta = hasta <= desde ? Date.parse("dd/MM/yyyy", "01/"+hasta+"/"+(anoDesde+1)) : Date.parse("dd/MM/yyyy", "01/"+hasta+"/"+anoDesde)
		fechaHasta = ultimoDiaDelMes(fechaHasta)

		return [fechaDesde, fechaHasta]
	}

	ArrayList<Date> mesesEntreFechas(Date fechaDesde, Date fechaHasta){
		ArrayList<Date> meses = []

		Calendar calDesde = Calendar.getInstance()
		calDesde.setTime(fechaDesde)

		Calendar calHasta = Calendar.getInstance()
		calHasta.setTime(fechaHasta)

		meses.add(formatearFechaPrimerDiaMes(calDesde.getTime()))
		calDesde.add(Calendar.MONTH, 1)

		while(formatearFechaPrimerDiaMes(calDesde.getTime()) <= formatearFechaPrimerDiaMes(calHasta.getTime())){
			meses.add(formatearFechaPrimerDiaMes(calDesde.getTime()))
			calDesde.add(Calendar.MONTH, 1)
		}

		return meses
	}

	int diasEntreFechas(Date fechaDesde, Date fechaHasta){
		return ((fechaHasta.getTime() - fechaDesde.getTime()) / (1000 * 60 * 60 * 24))
	}

	Date sumarMesesAFecha(Date fecha, int meses){
		Calendar cal = Calendar.getInstance();
		cal.setTime(fecha);
		cal.add(Calendar.MONTH, meses);
		Date nueva = cal.getTime();

		return nueva
	}

	Date sumarDiasAFecha(Date fecha, int dias){
		Calendar cal = Calendar.getInstance();
		cal.setTime(fecha);
		cal.add(Calendar.DATE, dias);
		Date nueva = cal.getTime();

		return nueva
	}

	Date sumarHorasAFecha(Date fecha, int horas){
		Calendar cal = Calendar.getInstance();
		cal.setTime(fecha);
		cal.add(Calendar.HOUR, horas);
		Date nueva = cal.getTime();

		return nueva
	}

	Date sumarMinutosAFecha(Date fecha, int minutos){
		Calendar cal = Calendar.getInstance();
		cal.setTime(fecha);
		cal.add(Calendar.MINUTE, minutos);
		Date nueva = cal.getTime();

		return nueva
	}

	Date parsear(String fecha,String formato){
		Date f=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat(formato);
		f=sdf.parse(fecha);

		return f;
	}

	Date setHoraEnFecha(String fecha,int hora){
		Date f=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat('dd/MM/yyyy HH:mm:ss');
		f=sdf.parse(fecha+" "+hora+":00"+":00");

		return f;
	}

	Date setHoraYMinutosEnFecha(String fecha,int hora,int minutos){
		Date f=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat('dd/MM/yyyy HH:mm:ss');
		f=sdf.parse(fecha+" "+hora+":"+minutos+":00");

		return f;
	}


//	enviar fecha en formato 'dd/MM/yyyy HH'
	Date setMinutosEnFecha(String fecha,int minutos){
		Date f=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat('dd/MM/yyyy HH:mm:ss');
		f=sdf.parse(fecha+":"+minutos+":00");

		return f;
	}

	String getDiaDeLaSemanaES(Date fecha){
		SimpleDateFormat simpleDateformat = new SimpleDateFormat("EEEE")
		String day = simpleDateformat.format(fecha)
		day = Normalizer.normalize(day, Normalizer.Form.NFD);
		day = day.replaceAll("[^\\p{ASCII}]", "");

		return day.capitalize()
	}

	String decimalToHourString(double hour){
		double hora = Math.floor(hour)
		double minute = hour - Math.floor(hour)
		return hora.toInteger() + ":" + (minute * 100).toInteger()
	}

	double stringHourToDecimal(String hour){
		ArrayList<String> horaSplit = hour.split(":")
		double hora = Double.parseDouble(horaSplit[0])
		def minute = horaSplit[1].toInteger() / 60
		return hora + minute
	}

	Date roundToHalfHourDown(Date fecha){
		Calendar cal = Calendar.getInstance()
		cal.setTime(fecha)
		cal.add(Calendar.MINUTE, - cal.get(Calendar.MINUTE))
		cal.add(Calendar.MINUTE, 30)

		Date fecha30 = cal.getTime()

		cal.setTime(fecha)
		cal.add(Calendar.MINUTE, - cal.get(Calendar.MINUTE))

		Date fecha00 = cal.getTime()

		return (fecha < fecha30 ? fecha00 : fecha30)
	}

	/*String getDiaDeLaSemanaES(Date fecha){
		String day = getDiaDeLaSemanaEN(fecha)
		String dia = null

		switch (day){
			case "Monday":
				dia = "Lunes"
				break
			case "Tuesday":
				dia = "Martes"
				break
			case "Wednesday":
				dia = "Miercoles"
				break
			case "Thursday":
				dia = "Jueves"
				break
			case "Friday":
				dia = "Viernes"
				break
			case "Saturday":
				dia = "Sabado"
				break
			case "Sunday":
				dia = "Domingo"
				break
		}

		return dia
	}*/
}
