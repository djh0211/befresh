import InfoTemp from "../components/templates/infoTemp";
import { getInfo } from "../api/info/infoApi";
import { useEffect, useState } from "react";
import { getAccessToken } from "../utils/tokenUtils";
import { informationType } from "../types/informationTypes";

function InfoPage() {
  const token = getAccessToken()
  const [containerInfo, setContainerInfo] = useState<informationType[]>([])

  const fetchInfo = async (token:string) => {
    const info = await getInfo(token)
      if (info) {
        setContainerInfo(info)
      }
  }
  useEffect(() => {
    if (token) {
      fetchInfo(token)
    }
  }, [])

  return (
    <InfoTemp containerInfo={containerInfo}/>
  );
}

export default InfoPage;
