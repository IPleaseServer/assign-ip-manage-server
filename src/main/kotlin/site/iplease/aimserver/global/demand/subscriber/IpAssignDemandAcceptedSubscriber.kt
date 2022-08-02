package site.iplease.aimserver.global.demand.subscriber

import site.iplease.aimserver.global.demand.data.message.IpAssignDemandAcceptedMessage

interface IpAssignDemandAcceptedSubscriber {
    fun subscribe(message: IpAssignDemandAcceptedMessage)
}