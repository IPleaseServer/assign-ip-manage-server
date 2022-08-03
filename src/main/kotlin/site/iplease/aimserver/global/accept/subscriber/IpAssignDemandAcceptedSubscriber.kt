package site.iplease.aimserver.global.accept.subscriber

import site.iplease.aimserver.global.accept.data.message.IpAssignDemandAcceptedMessage

interface IpAssignDemandAcceptedSubscriber {
    fun subscribe(message: IpAssignDemandAcceptedMessage)
}