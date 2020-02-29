

class ServiceInfo(val id: String, val name: String) {

}

class AreaInfo(val id: String, val name: String, val description: String, val arguments: Array<String>, val service: ServiceInfo) {

}

class MyLinks(val id: String, val name: String, val timeCheck: String, val lastRun: String, val action: AreaInfo, val reaction: AreaInfo) {

}